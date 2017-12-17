package com.ecnu.trivial.dto;

/**
 * Created by 11022 on 2017/6/29.
 */
public class BaseBean {
    private String singleResult;

    public BaseBean() {
        singleResult = "";
    }

    public String getSingleResult() {
        return singleResult;
    }

    public void setSingleResult(String singleResult) {
        this.singleResult = singleResult;
    }
}
