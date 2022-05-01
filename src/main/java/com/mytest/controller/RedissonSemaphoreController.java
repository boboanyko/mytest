package com.mytest.controller;


import com.mytest.service.RedissonSemaphoreTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson/semaphore")
@Slf4j
public class RedissonSemaphoreController {


    @Autowired
    RedissonSemaphoreTestService redissonSemaphoreTestService;


    @GetMapping(value = "/test/redisson/semaphore")
    public String redissonRedLock() throws InterruptedException {
        redissonSemaphoreTestService.testSemaphore();
        return "success";
    }
}
