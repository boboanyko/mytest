package com.mytest.service.async.impl;

import com.alibaba.fastjson.JSONObject;
import com.mytest.service.async.AsyncTest2Service;
import com.mytest.service.async.AsyncTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class AsyncTestServiceImpl implements AsyncTestService {

    @Autowired
    private AsyncTest2Service asyncTest2Service;

    @Autowired
    private AsyncTest3ServiceImpl asyncTest3Service;

    @Override
    public String asyncTest() {

        String s3 = asyncTest3Service.asyncTest3();
        String s2 = asyncTest2Service.asyncTest2();

        String s = asyncOne();
        String s1 = asyncTwo();

        return s+s1 + s2 + s3;
    }


    @Async
    public String asyncOne() {
        log.info("asyncOne - start - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncOne - end - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "1";
    }


    @Async
    public String asyncTwo() {
        log.info("asyncTwo - start  - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("asyncTwo - end  - {}", DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm:ss"));

        return "2";
    }
}
