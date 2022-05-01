package com.mytest.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public String testException(HttpServletRequest request,Throwable e) {
        log.info(request.getContextPath());
        log.info(request.getRequestURL().toString());
        log.info(request.getRequestURI());
        log.info("test",e);
        return "test exception";
    }
}
