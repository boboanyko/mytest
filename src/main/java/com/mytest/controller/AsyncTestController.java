package com.mytest.controller;


import com.mytest.service.async.AsyncTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncTestController {


    @Autowired
    private AsyncTestService asyncTestService;

    @RequestMapping("/test")
    public String testAsync() {
        String s = asyncTestService.asyncTest();
        return s;
    }
}
