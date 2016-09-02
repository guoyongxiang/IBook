package com.rwcc.ibook.model.bean;

import com.google.gson.GsonBuilder;

public class BaseRequestBean {
    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }
}
