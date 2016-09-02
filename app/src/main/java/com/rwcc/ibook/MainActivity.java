package com.rwcc.ibook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView aa;
        aa = (TextView)findViewById(R.id.aa);
        aa.setText(BuildConfig.BASE_SERVER);

        //调用retrofit
//        NetMoDel.login(loginRequestBean, new BaseRequestCallBack<LoginResponseBean>(getView()) {
//            @Override
//            protected void onRequestFailure(Throwable throwable) {
//                getView().onRequestFailure(throwable);
//                Logger.e(throwable, "test");
//            }
//
//            @Override
//            protected void onRequestSuccess(final BaseResponseBean<LoginResponseBean> body) {
//                saveRealm(body);
//            }
//        });
    }
}
