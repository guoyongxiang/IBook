package com.rwcc.ibook.model.bean;


import com.google.gson.GsonBuilder;

public class BaseResponseBean<M> {

    /**
     * cardBarcode : http://120.24.215.97/rescoure/SW/NzAwMDc5Mjg0fDIx.jpg
     * birthday : 2016-01-01
     * totalScore : 10000
     * toalPeriodsEndDate : 2016-07-31
     * bloomingClub : false
     * favourReadingType :
     * firstLogin : 0
     * acceptFeedNewBook : false
     * noOfChildren :
     * emailAddr : 1051524848@qq.com
     * cardQuality : 1
     * cardNo : 700079284
     * firstNameCh : ma1
     * firstNameEn : ma1
     * acceptFeedNewActivity : false
     * lastNameCh : weijie1
     * gender : 2
     * effectiveScore : 5000
     * coupon : false
     * dealLine : 2017-05-31
     * customersId : 100380490
     * lastNameEn : weijie1
     * authorizeCode : 4a1ea55c8b11ce91d138e4ef8f6192b1
     * toalPeriods : 0
     * levelCode : BASIC
     * cardType : 21
     * country :
     * discount : [{"discountCode":"","discountAmount":0}]
     * phoneNo : 123456
     * month : 1
     * continuedCardInfo : true
     */

    private M msgDesc;
    /**
     * retCode : 0
     * message : success
     */

    private OpResultEntity opResult;

    public M getMsgDesc() {
        return msgDesc;
    }

    public void setMsgDesc(M msgDesc) {
        this.msgDesc = msgDesc;
    }

    public OpResultEntity getOpResult() {
        return opResult;
    }

    public void setOpResult(OpResultEntity opResult) {
        this.opResult = opResult;
    }
    public static class OpResultEntity {
        public OpResultEntity() {
        }

        public OpResultEntity(int retCode, String message) {
            this.retCode = retCode;
            this.message = message;
        }

        private int retCode;
        private String message;

        public int getRetCode() {
            return retCode;
        }

        public void setRetCode(int retCode) {
            this.retCode = retCode;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}