package com.chainxi.common.web.pojo;

import com.chainxi.common.web.constant.HttpCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serial;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    private ResponseResult() {

    }

    public Integer getCode() {
        return code;
    }

    public ResponseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResponseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <R> ResponseResult<R> success(R data) {
        return new ResponseResult<R>().setCode(HttpCodeEnum.SUCCESS.getCode())
                .setMsg(HttpCodeEnum.SUCCESS.getMessage())
                .setData(data);
    }


    public static ResponseResult success() {
        return ResponseResult.success(null);
    }


    public static <R> ResponseResult<R> error(R data) {
        return new ResponseResult<R>()
                .setCode(HttpCodeEnum.FAIL.getCode())
                .setMsg(HttpCodeEnum.FAIL.getMessage())
                .setData(data);
    }

    public static ResponseResult error() {
        return error(null);
    }

    public static <R> ResponseResult<R> of(R data, Integer code, String msg) {
        return new ResponseResult<R>().setCode(code).setMsg(msg).setData(data);
    }

    public static <R> ResponseResult<R> of(Integer code, String msg) {
        return of(null, code, msg);
    }

    public static <R> ResponseResult<R> of(Integer code) {
        return of(null, code, null);
    }
}

