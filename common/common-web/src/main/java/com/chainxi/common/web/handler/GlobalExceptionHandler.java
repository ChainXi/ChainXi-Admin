package com.chainxi.common.web.handler;

import com.chainxi.common.web.exception.BizException;
import com.chainxi.common.web.pojo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ResponseResult handlerServiceException(Exception e) {
        log.error(e.getMessage(),e);
        return ResponseResult.error().setMsg(e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public ResponseResult handlerServiceException(BizException e) {
        return e.getResponseResult();
    }
}