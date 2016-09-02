package com.rwcc.ibook.model.server.retrofit;

import android.util.Pair;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rwcc.ibook.BuildConfig;
import com.rwcc.ibook.model.bean.BaseResponseBean;
import com.rwcc.ibook.model.server.Interceptor.HttpLogInterceptor;


import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitServer {
    private static final String sApiHost = BuildConfig.BASE_SERVER;

    private static final Gson sGson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setExclusionStrategies(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    //return f.getDeclaringClass().equals(RealmObject.class);
                    return true;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            }).create();

    private static final GsonConverterFactory sFactory = GsonConverterFactory.create(sGson);



    private static final HttpLogInterceptor sInterceptor = new HttpLogInterceptor(){

        /**
         * 这个方法不用管，是接口格式返回问题，以后或许会删除
         */
        @Override
        public Pair<Boolean, String> transformerJson(String json) {
            BaseResponseBean bean = new BaseResponseBean<String>();
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject opResult = jsonObject.getJSONObject("opResult");
                int retCode = opResult.getInt("retCode");
                if (retCode == 0) {
                    throw new JSONException("code");
                }
                String msgDesc;
                try {
                     msgDesc = jsonObject.getString("msgDesc");
                } catch (Exception e) {
                    msgDesc = "";
                }

                bean.setOpResult(new BaseResponseBean.OpResultEntity(retCode, msgDesc));
                return new Pair<>(true, bean.toString());
            } catch (JSONException e) {
//                e.printStackTrace();
                return super.transformerJson(json);
            }
        }
    };

    private static final OkHttpClient sClient = new OkHttpClient.Builder()
            .addInterceptor(sInterceptor)
            .build();

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(sApiHost)
            .client(sClient)
            .addConverterFactory(sFactory)
            .build();

    private static final IRetrofitServer sIRetrofitServer = sRetrofit.create(IRetrofitServer.class);

//    public static void login(LoginRequestBean loginRequestBean, BaseRequestCallBack baseRequestCallBack) {
//        sIRetrofitServer.login(loginRequestBean).enqueue(baseRequestCallBack);
//    }
//
//
//    public static void register(RegisterRequestBean registerRequestBean, BaseRequestCallBack baseRequestCallBack) {
//        sIRetrofitServer.register(registerRequestBean).enqueue(baseRequestCallBack);
//    }
//
//    public static void query(AuthorizeCodeRequestBean authorizeCodeRequestBean, BaseRequestCallBack baseRequestCallBack) {
//        sIRetrofitServer.query(authorizeCodeRequestBean).enqueue(baseRequestCallBack);
//    }
//
//    public static void reset(ResetRequestBean resetRequestBean, BaseRequestCallBack baseRequestCallBack) {
//        sIRetrofitServer.reset(resetRequestBean).enqueue(baseRequestCallBack);
//    }
//
//    public static void giftinfo(GiftinfoRequestBean giftinfoRequestBean, BaseRequestCallBack baseRequestCallBack) {
//        sIRetrofitServer.giftinfo(giftinfoRequestBean).enqueue(baseRequestCallBack);
//    }
//
//    public static void category(CategoryRequestBean categoryRequestBean,BaseRequestCallBack<CategoryResponseBean> beanBaseRequestCallBack) {
//        sIRetrofitServer.category(categoryRequestBean).enqueue(beanBaseRequestCallBack);
//    }
//
//    public static void products(ProductsRequestBean productsRequestBean, BaseRequestCallBack<ProductsResponseBean> baseRequestCallBack) {
//        sIRetrofitServer.products(productsRequestBean).enqueue(baseRequestCallBack);
//    }
//
//    public static void productDetail(ProductDetailRequestBean productDetailRequestBean, BaseRequestCallBack<ProductDetailResponseBean> beanBaseRequestCallBack) {
//        sIRetrofitServer.productDetail(productDetailRequestBean).enqueue(beanBaseRequestCallBack);
//    }
//
//    public static void productClassify(ProductClassifyRequestBean productClassifyRequestBean, BaseRequestCallBack<ProductsResponseBean> baseRequestCallBack) {
//        sIRetrofitServer.productClassify(productClassifyRequestBean).enqueue(baseRequestCallBack);
//    }
}
