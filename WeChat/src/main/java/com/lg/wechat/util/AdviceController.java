package com.lg.wechat.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception ex )  {
        return  Optional.ofNullable(ex.getMessage()).orElseGet(() -> new Exception("请稍后重试！").getMessage());
    }
}