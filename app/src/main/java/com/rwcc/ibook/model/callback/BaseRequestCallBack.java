package com.rwcc.ibook.model.callback;



import com.rwcc.ibook.model.bean.BaseResponseBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class BaseRequestCallBack<M> implements Callback<BaseResponseBean<M>> {

    private final IView mView;

    public BaseRequestCallBack(IView view) {
        mView = view;

        ThreadUtil.postTaskSafely(new Runnable() {
            @Override
            public void run() {
                onPostRequestExecute();
            }
        });
    }

    private void onPostRequestExecute() {
        mView.onPostRequestExecute();
    }

    @Override
    public void onResponse(Call<BaseResponseBean<M>> call, Response<BaseResponseBean<M>> response) {
        onRequestComplete();

        BaseResponseBean<M> body = response.body();
        BaseResponseBean.OpResultEntity opResult = body.getOpResult();
        if (opResult == null) {
            onRequestFailure(new Throwable("data error"));
        }

        if ((opResult != null ? opResult.getRetCode() : 0) == 0) {
            onRequestSuccess(body);
        } else {
            onRequestError(body.getOpResult());
        }
    }

    private void onRequestComplete() {
        mView.onRequestComplete();
    }

    protected  void onRequestFailure(Throwable throwable){
       // Logger.e(throwable,"");
        mView.onRequestFailure(throwable);
    };

    protected abstract void onRequestSuccess(BaseResponseBean<M> body);

    protected void onRequestError(BaseResponseBean.OpResultEntity opResult){
        mView.onRequestError(opResult);
    }

    @Override
    public void onFailure(Call<BaseResponseBean<M>> call, Throwable t) {
        onRequestComplete();
        onRequestFailure(t);
    }
}
