package com.rwcc.ibook.model.server.retrofit;


import com.yuntong.memberservice.model.bean.BaseResponseBean;
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
import com.yuntong.memberservice.model.bean.response_bean.LoginResponseBean;
import com.yuntong.memberservice.model.bean.response_bean.ProductDetailResponseBean;
import com.yuntong.memberservice.model.bean.response_bean.ProductsResponseBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface IRetrofitServer {
    @FormUrlEncoded
    @POST("api/user/login.action")
    Call<BaseResponseBean<LoginResponseBean>> login(@Field("interParam") LoginRequestBean loginRequestBean);

    @FormUrlEncoded
    @POST("api/user/register.action")
    Call<BaseResponseBean> register(@Field("interParam") RegisterRequestBean registerRequestBean);

    @FormUrlEncoded
    @POST("api/user/query.action")
    Call<BaseResponseBean> query(@Field("interParam") AuthorizeCodeRequestBean authorizeCodeRequestBean);

    @FormUrlEncoded
    @POST("api/user/reset.action")
    Call<BaseResponseBean> reset(@Field("interParam") ResetRequestBean resetRequestBean);

    @FormUrlEncoded
    @POST("api/user/giftinfo.action")
    Call<BaseResponseBean> giftinfo(@Field("interParam") GiftinfoRequestBean giftinfoRequestBean);

    @FormUrlEncoded
    @POST("api/user/category.action")
    Call<BaseResponseBean<CategoryResponseBean>> category(@Field("interParam") CategoryRequestBean categoryRequestBean);

    @FormUrlEncoded
    @POST("api/user/products.action")
    Call<BaseResponseBean<ProductsResponseBean>> products(@Field("interParam") ProductsRequestBean productsRequestBean);

    @FormUrlEncoded
    @POST("api/user/productDetail.action")
    Call<BaseResponseBean<ProductDetailResponseBean>> productDetail(@Field("interParam") ProductDetailRequestBean productDetailRequestBean);

    @FormUrlEncoded
    @POST("api/user/productClassify.action")
    Call<BaseResponseBean<ProductsResponseBean>> productClassify(@Field("interParam") ProductClassifyRequestBean productClassifyRequestBean);
}
