package com.mytest.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionTestController {


    @RequestMapping("/test/excep")
    public void testException() {
        throw new NullPointerException();
    }
}
