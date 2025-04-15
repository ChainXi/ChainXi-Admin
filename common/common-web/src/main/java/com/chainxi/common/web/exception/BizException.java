package com.chainxi.common.web.exception;


import com.chainxi.common.web.pojo.ResponseResult;
import lombok.Data;

import java.io.Serial;

/**
 * 业务逻辑异常 Exception
 */
@Data
public final class BizException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -1L;
    private final ResponseResult responseResult;

    public BizException(ErrorCode errorCode,String... args) {
        responseResult =
                ResponseResult.error().setCode(errorCode.getCode()).setMsg(errorCode.getMsg());
    }
}
