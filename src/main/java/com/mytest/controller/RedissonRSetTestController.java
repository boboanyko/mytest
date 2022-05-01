package com.mytest.controller;


import com.mytest.service.RedissonLockTestService;
import com.mytest.service.RedissonRSetTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson/rset")
@Slf4j
public class RedissonRSetTestController {


    @Autowired
    RedissonRSetTestService redissonRSetTestService;



    @GetMapping(value = "/test/{key}/{value}")
    public Integer testRedisson(@PathVariable String key, @PathVariable String value) {
        return redissonRSetTestService.testRSet(key, value);
    }


    @GetMapping(value = "/test/bucket/{key}/{value}")
    public Boolean testRBucket(@PathVariable String key, @PathVariable String value) {
        return redissonRSetTestService.testRBucket(key, value);
    }

}
