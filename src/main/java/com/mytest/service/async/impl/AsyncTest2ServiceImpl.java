package com.mytest.service.async.impl;

import com.mytest.service.async.AsyncTest2Service;
import com.mytest.service.async.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AsyncTest2ServiceImpl implements AsyncTest2Service {


    @Override
    @Async
    public String asyncTest2() {
        String s = asyncOne();
        String s1 = asyncTwo();
        return s+s1;
    }


    @Async
    public String asyncOne() {
        log.info("async2One - start - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async2One - end - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "1";
    }


    @Async
    public String asyncTwo() {
        log.info("async2Two - start - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("async2Two - end - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "2";
    }
}
