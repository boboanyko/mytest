package com.mytest.service;

public interface RedissonLockTestService {

    Boolean testRedissonLockByKey(String key,String flag,Integer lockTime);

    Boolean testRedissonLock(String flag,Integer lockTime);

    void putRedissonValue(String key, String value);

    String getRedissonValue(String key);

    boolean getLockTest(String key);

    Boolean testRedissonMultiLock(String flag, Integer lockTime);

    Boolean testRedissonRedLock(String flag, Integer lockTime);
}
