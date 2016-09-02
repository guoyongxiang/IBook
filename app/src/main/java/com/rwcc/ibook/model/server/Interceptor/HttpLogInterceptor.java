package com.rwcc.ibook.model.server.Interceptor;

import android.util.Pair;

import com.orhanobut.logger.Logger;
import com.yuntong.memberservice.BuildConfig;
import com.yuntong.memberservice.model.utils.Md5Util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class HttpLogInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        String interParam = null;
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();

        if (original.body() instanceof FormBody) {
            FormBody.Builder newFormBody = new FormBody.Builder();
            FormBody oldFormBody = (FormBody) original.body();
            for (int i = 0; i < oldFormBody.size(); i++) {
                String value = oldFormBody.encodedValue(i);
                if (i == 0) {
                    value = URLDecoder.decode(value, "UTF-8");
                    interParam = value;
                }
                newFormBody.addEncoded(oldFormBody.encodedName(i), value);
            }

            addParams(interParam, newFormBody);

            FormBody build = newFormBody.build();

            printRequestLog(build);

            requestBuilder.method(original.method(), build);
        }

        Request request = requestBuilder.build();
        Response response = chain.proceed(request);

        ResponseBody body = response.body();
        String json = printResponseLog(body);

        return transformerData(response, json);
    }

    private void addParams(String interParam, FormBody.Builder newFormBody) {
        String busiSecKey = BuildConfig.BUSI_SEC_KEY;
        newFormBody.add("busiSecKey", busiSecKey);
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        newFormBody.add("timeStamp", timeStamp);
        String inbuf = interParam + busiSecKey + timeStamp;
//        String md5ofStr = new MD5().getMD5ofStr(inbuf);
        String md5ofStr = Md5Util.getMD5Code(inbuf);
        newFormBody.add("hmac", md5ofStr);
    }

    private void printRequestLog(FormBody build) throws UnsupportedEncodingException {
        String str = build.encodedValue(0);

        String decode = URLDecoder.decode(str, "UTF-8");

        String json = "{Request<interParam>:" + decode + "}";

        try {
            Logger.json(json);
        } catch (Exception e) {
            Logger.e("Json serialization failure\n\n" + json);
        }

        StringBuilder log = new StringBuilder();
        for (int i = 1; i < build.size(); i++) {
            String value = build.encodedValue(i);
            String decodeValue = URLDecoder.decode(value, "UTF-8");
            log.append("key   : ")
                    .append(build.encodedName(i))
                    .append("\n")
                    .append("value : ")
                    .append(decodeValue)
                    .append("\n");
        }

        Logger.d(log.toString());
    }

    private String printResponseLog(ResponseBody responseBody) throws IOException {
        String rawJson = null;

        long contentLength = responseBody.contentLength();

        if (contentLength != 0) {
            BufferedSource source = responseBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            rawJson = buffer.clone().readString(Charset.forName("UTF-8"));
           String json = "{Response:" + rawJson + "}";

            Logger.d(json);

            try {
                Logger.json(json);
            } catch (Exception e) {
                Logger.e("Json serialization failure\n\n" + json);
            }
        }
        return rawJson;
    }

    public Response transformerData(Response response, String json) {
        Pair<Boolean, String> booleanStringPair = transformerJson(json);
        if (booleanStringPair.first) {
            BufferedSource source = response.body().source();
            Buffer buffer = source.buffer();
            buffer.clear();
            buffer.writeString(booleanStringPair.second, Charset.forName("UTF-8"));
            return response.newBuilder().body(new RealResponseBody(response.headers(), source)).build();
        }
        return response;
    }

    public Pair<Boolean, String> transformerJson(String json) {
        return new Pair<>(false, json);
    }
}
