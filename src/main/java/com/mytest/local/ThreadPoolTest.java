package com.mytest.local;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;


@Slf4j
public class ThreadPoolTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //创建线程池，并获得线程池管理对象
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60,
//                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.CallerRunsPolicy());
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(2, 2, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
        FutureTask<?> producerTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + "子线程执行了");
            //线程内部的线程池
//            ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 1, 60,
//                    TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new ThreadPoolExecutor.CallerRunsPolicy());


            List<Integer> processTaskList = new ArrayList<>();
            for (int i = 1; i <= 100; i++) {
                processTaskList.add(i);
            }
            List<CompletableFuture<String>> futureTaskList = processTaskList.stream().map(v ->
                    CompletableFuture.supplyAsync(() -> {
                        System.out.println(Thread.currentThread().getName() + "执行了" + "[" + v + "]");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                        return String.valueOf(v);
                    }, threadPoolExecutor1)).collect(Collectors.toList());
            System.out.println("这里没有执行");
            CompletableFuture<?>[] futureTaskArr = new CompletableFuture[futureTaskList.size()];
            CompletableFuture<?>[] completableFutures = futureTaskList.toArray(futureTaskArr);
            CompletableFuture<Void> allFuture = CompletableFuture.allOf(completableFutures);
            CompletableFuture<List<String>> resFuture = allFuture.thenApply(v ->
                    futureTaskList.stream().map(CompletableFuture::join).collect(Collectors.toList()));
            return resFuture.get(20000, TimeUnit.MILLISECONDS).size();
        });
        threadPoolExecutor1.submit(producerTask);
        System.out.println("process:"+ producerTask.get());
    }


}
