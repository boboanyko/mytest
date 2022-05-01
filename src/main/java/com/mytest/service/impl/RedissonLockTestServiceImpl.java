package com.mytest.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mytest.service.RedissonLockTestService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedissonLockTestServiceImpl implements RedissonLockTestService {

    @Autowired
    private RedissonClient redissonClient;






    @Override
    public void putRedissonValue(String key, String value) {
        RMap<String, String> map = redissonClient.getMap("test1");
        map.put(key, value);
    }

    @Override
    public String getRedissonValue(String key) {
        RMap<String, String> map = redissonClient.getMap("test1");
        return map.get(key);
    }


    @Override
    public boolean getLockTest(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }





    @Override
    public Boolean testRedissonMultiLock(String flag,Integer lockTime) {
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");

        RedissonMultiLock multiLock = new RedissonMultiLock(lock1, lock2, lock3);

        try {
            boolean multi_lock_try_result = multiLock.tryLock(-1L, lockTime, TimeUnit.SECONDS);
            log.info("multi_lock_try_result -- {}  --> {}", flag,  multi_lock_try_result);

//            if (multi_lock_try_result) {
//                multiLock.lock(lockTime, TimeUnit.SECONDS);
//            } else {
//                log.info("multi_lock is locked, and please wait -- {} -- seconds to reLock",lockTime);
//            }

            return multi_lock_try_result;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }


    @Override
    public Boolean testRedissonRedLock(String flag, Integer lockTime) {
        RLock lock1 = redissonClient.getLock("lock1");
        RLock lock2 = redissonClient.getLock("lock2");
        RLock lock3 = redissonClient.getLock("lock3");

        RedissonMultiLock redLock = new RedissonRedLock(lock1, lock2, lock3);

        try {
            boolean red_lock_try_result = redLock.tryLock();
            log.info("multi_lock_try_result -- {}  --> {}", flag,  red_lock_try_result);

            if (red_lock_try_result) {
                redLock.lock(lockTime, TimeUnit.SECONDS);
            } else {
                log.info("multi_lock is locked, and please wait -- {} -- seconds to reLock",lockTime);
            }

            return red_lock_try_result;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return false;
    }



    @Override
    public Boolean testRedissonLock(String flag,Integer lockTime) {
        return testRedissonLockByKey("local", flag, lockTime);
    }

    @Override
    public Boolean testRedissonLockByKey(String key,String flag,Integer lockTime) {
        String lockKey = key+ "_lock";
        RLock localhost_lock = redissonClient.getLock(key);
        log.info("-- {} -- info -- {} --> {}",lockKey ,flag, JSONObject.toJSONString(localhost_lock));

        try {
            boolean localhost_lock_try_result = localhost_lock.tryLock();
            log.info("-- {} --_try_result -- {}  --> {}",lockKey, flag,  localhost_lock_try_result);

            if (localhost_lock_try_result) {
                localhost_lock.lock(lockTime, TimeUnit.SECONDS);
            } else {
                log.info("-- {} -- is locked, and please wait -- {} -- seconds to reLock",lockKey,lockTime);
            }

            return localhost_lock_try_result;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }


}
