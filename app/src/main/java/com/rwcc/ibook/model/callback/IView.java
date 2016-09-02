package com.rwcc.ibook.model.callback;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.rwcc.ibook.model.bean.BaseResponseBean;


public abstract class IView {

    private Context mContext;
    private ProgressDialog mProgressDialog;

    public IView(Context context) {
        mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

    public void onPostRequestExecute() {
        if (isHaveContent())
            onLoading();
        else
            onRefreshing();
    }

    public boolean isHaveContent() {
        return true;
    }

    public void onLoading() {
        showProgressDialog();
    }

    public void onRefreshing() {
    }

    public void showProgressDialog() {
        getProgressDialog().show();
    }

    @NonNull
    public ProgressDialog getProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("連接中...");
            mProgressDialog.setCancelable(false);
            return mProgressDialog;
        }
        return mProgressDialog;
    }

    public void onRequestComplete() {
        if (isHaveContent())
            onLoaded();
        else
            onRefreshed();
    }

    public void onLoaded() {
        DismissProgressDialog();
    }

    public void onRefreshed() {

    }

    public void DismissProgressDialog() {
        if (mProgressDialog != null)
            mProgressDialog.dismiss();
    }

    public void onRequestFailure(Throwable t){
        if (isHaveContent())
            onLoadingFailure(t);
        else
            onRefreshFailure(t);
    }

    protected  void onRefreshFailure(Throwable t){}

    protected abstract void onLoadingFailure(Throwable t);

    public  void onRequestError(BaseResponseBean.OpResultEntity opResult){
        if (isHaveContent())
            onLoadingError(opResult);
        else
            onRefreshError(opResult);
    }

    protected  void onRefreshError(BaseResponseBean.OpResultEntity opResult){}

    protected abstract void onLoadingError(BaseResponseBean.OpResultEntity opResult);
}
