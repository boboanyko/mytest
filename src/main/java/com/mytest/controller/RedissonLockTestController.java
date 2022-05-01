package com.mytest.controller;


import com.mytest.service.RedissonLockTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redisson")
@Slf4j
public class RedissonLockTestController {


    @Autowired
    RedissonLockTestService redissonTestService;

    @GetMapping(value = "/helloworld/{param}")
    public String helloworld(@PathVariable String param) {
        log.info("test helloworld!");
        return "i can receive " + param;
    }


    @GetMapping(value = "/test/redisson/{key}/{value}")
    public String testRedisson(@PathVariable String key, @PathVariable String value) {
        redissonTestService.putRedissonValue(key, value);

        return redissonTestService.getRedissonValue(key);
    }

    @GetMapping(value = "/test/redisson/get/{key}")
    public String getRedisson(@PathVariable String key) {
        return redissonTestService.getRedissonValue(key);
    }

    @GetMapping(value = "/test/redisson/get/lock/{key}")
    public String getLockTest(@PathVariable String key) {
        Boolean lockTest = redissonTestService.getLockTest(key);
        return lockTest.toString();
    }

    @GetMapping(value = "/test/redisson/lock/{key}/{flag}/{lockTime}")
    public String redissonLockByKey(@PathVariable String key, @PathVariable String flag, @PathVariable Integer lockTime) {

        Boolean aBoolean = redissonTestService.testRedissonLockByKey(key, flag, lockTime);
        return aBoolean.toString();
    }

    @GetMapping(value = "/test/redisson/lock/{flag}/{lockTime}")
    public String redissonLock(@PathVariable String flag, @PathVariable Integer lockTime) {
        Boolean aBoolean = redissonTestService.testRedissonLock(flag, lockTime);
        return aBoolean.toString();
    }

    @GetMapping(value = "/test/redisson/multi/lock/{flag}/{lockTime}")
    public String redissonMultiLock(@PathVariable String flag, @PathVariable Integer lockTime) {
        Boolean aBoolean = redissonTestService.testRedissonMultiLock(flag,lockTime);
        return aBoolean.toString();
    }


    @GetMapping(value = "/test/redisson/red/lock/{flag}/{lockTime}")
    public String redissonRedLock(@PathVariable String flag, @PathVariable Integer lockTime) {
        Boolean aBoolean = redissonTestService.testRedissonRedLock(flag,lockTime);
        return aBoolean.toString();
    }
}
