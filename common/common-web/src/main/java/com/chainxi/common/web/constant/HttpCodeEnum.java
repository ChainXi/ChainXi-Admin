package com.chainxi.common.web.constant;

public enum HttpCodeEnum {

    // 成功段固定为200
    SUCCESS(200, "success"),
    FAIL(400, "fail");


    int code;
    String message;

    HttpCodeEnum(int code, String message) {
        this.code    = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}