package com.mytest.service.impl;

import com.mytest.service.RedissonRSetTestService;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedissonRSetServiceImpl implements RedissonRSetTestService {

    @Autowired
    private RedissonClient redissonClient;

    static final String REDIS_KEY = "rset_test1:";

    @Override
    public Integer testRSet(String key, String value) {
        RSet<Object> set = redissonClient.getSet(REDIS_KEY + key);
        System.out.println(set.contains(value));
        if (!set.contains(value)) {
            set.add(value);
        }
        return set.size();
    }

    @Override
    public Boolean testRSet(String key, String flag, Integer lockTime) {
        return null;
    }

    @Override
    public Boolean testRBucket(String key, String value) {
        RBucket rBucket = redissonClient.getBucket(REDIS_KEY + key);
        System.out.println(rBucket.get() == null);
        if (rBucket.get() == null) {
            rBucket.set(value);
            rBucket.set(value,10, TimeUnit.SECONDS);
            return true;
        }
        
        return false;
    }
}
