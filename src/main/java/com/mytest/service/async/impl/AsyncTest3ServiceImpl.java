package com.mytest.service.async.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AsyncTest3ServiceImpl{


    @Async
    public String asyncTest3() {
        String s = asyncOne();
        String s1 = asyncTwo();
        return s+s1;
    }


    @Async
    public String asyncOne() {
        log.info("async3One - start - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async3One - end - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "1";
    }


    @Async
    public String asyncTwo() {
        log.info("async3Two - start - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async3Two - end - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "2";
    }
}
