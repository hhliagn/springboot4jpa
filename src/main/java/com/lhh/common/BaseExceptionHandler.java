package com.lhh.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lhh
 * @date 2019/5/26 16:02
 */
@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result getException(Exception e){
        return new Result(StatusCode.ERROR, e.getMessage());
    }
}
