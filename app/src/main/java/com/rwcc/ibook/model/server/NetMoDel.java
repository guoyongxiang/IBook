package com.rwcc.ibook.model.server;

import com.yuntong.memberservice.model.bean.request_bean.AuthorizeCodeRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.CategoryRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.GiftinfoRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.LoginRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.ProductClassifyRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.ProductDetailRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.ProductsRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.RegisterRequestBean;
import com.yuntong.memberservice.model.bean.request_bean.ResetRequestBean;
import com.yuntong.memberservice.model.bean.response_bean.CategoryResponseBean;
import com.yuntong.memberservice.model.bean.response_bean.ProductDetailResponseBean;
import com.yuntong.memberservice.model.bean.response_bean.ProductsResponseBean;
import com.yuntong.memberservice.model.callback.BaseRequestCallBack;
import com.yuntong.memberservice.model.server.retrofit.RetrofitServer;

public class NetMoDel {
    public static void login(LoginRequestBean loginRequestBean, BaseRequestCallBack baseRequestCallBack) {
        RetrofitServer.login(loginRequestBean, baseRequestCallBack);
    }

    public static void register(RegisterRequestBean registerRequestBean, BaseRequestCallBack baseRequestCallBack) {
        RetrofitServer.register(registerRequestBean, baseRequestCallBack);
    }

    public static void query(AuthorizeCodeRequestBean authorizeCodeRequestBean, BaseRequestCallBack baseRequestCallBack) {
        RetrofitServer.query(authorizeCodeRequestBean, baseRequestCallBack);
    }

    public static void reset(ResetRequestBean resetRequestBean, BaseRequestCallBack baseRequestCallBack) {
        RetrofitServer.reset(resetRequestBean, baseRequestCallBack);
    }

    public static void giftinfo(GiftinfoRequestBean giftinfoRequestBean, BaseRequestCallBack baseRequestCallBack) {
        RetrofitServer.giftinfo(giftinfoRequestBean, baseRequestCallBack);
    }

    public static void category(CategoryRequestBean categoryRequestBean,BaseRequestCallBack<CategoryResponseBean> baseRequestCallBack) {
        RetrofitServer.category(categoryRequestBean,baseRequestCallBack);
    }

    public static void products(ProductsRequestBean productsRequestBean, BaseRequestCallBack<ProductsResponseBean> baseRequestCallBack) {
        RetrofitServer.products(productsRequestBean,baseRequestCallBack);
    }

    public static void productDetail(ProductDetailRequestBean productDetailRequestBean, BaseRequestCallBack<ProductDetailResponseBean> baseRequestCallBack) {
        RetrofitServer.productDetail(productDetailRequestBean,baseRequestCallBack);
    }

    public static void productClassify(ProductClassifyRequestBean productClassifyRequestBean, BaseRequestCallBack<ProductsResponseBean> baseRequestCallBack) {
        RetrofitServer.productClassify(productClassifyRequestBean,baseRequestCallBack);
    }
}
