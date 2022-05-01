package com.mytest.service;

public interface RedissonRSetTestService {

    Integer testRSet(String key, String flag);
    Boolean testRSet(String key,String flag,Integer lockTime);

    Boolean testRBucket(String key, String value);
}
