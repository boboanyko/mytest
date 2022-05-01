package com.mytest.service.impl;

import com.mytest.service.RedissonSemaphoreTestService;
import org.redisson.api.RSemaphore;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Service
public class RedissonSemaphoreTestServiceImpl implements RedissonSemaphoreTestService {

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void testSemaphore() throws InterruptedException {

        // 创建一个semaphore实例
        RSemaphore semaphore = redissonClient.getSemaphore("semaphore");
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // 将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + "over");
                    semaphore.release();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 将线程B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + "over");
                    semaphore.release();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // 等待子线程执行完毕，返回
        semaphore.acquire(3);
        System.out.println("all child thread over");

        // 关闭线程池
        executorService.shutdown();
    }
}
