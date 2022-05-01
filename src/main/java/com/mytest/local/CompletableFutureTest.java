package com.mytest.local;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureTest {

    public static void main(String[] args) {


        // runAsync
        // runAsync只是简单的异步执行一个线程，但是它将返回一个CompletableFuture
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println("只是一个线程而已");
            }
        });


//        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
//            try{
//                Thread.sleep(1000L);
//                return "test";
//            } catch (Exception e){
//                return "failed test";
//            }
//        });
//        future.complete("manual test");
//        System.out.println(future.join());
//
//
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 1");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("compute 1 wake up");
//            return 1;
//        });
//        CompletableFuture<Integer> future2 = future1.thenApply((p)->{
//            System.out.println("compute 2");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("compute 2 wake up");
//
//            return p+10;
//        });
//        System.out.println("result: " + future2.join());

        //thenCombine
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 1");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("compute 1 wake up");
//            return 1;
//        });
//        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 2");
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("compute 2 wake up");
//            return 10;
//        });
//        CompletableFuture<Integer> future3 = future1.thenCombine(future2, (r1, r2)->r1 + r2);
//        System.out.println("result: " + future3.join());


        // thenCompose???
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 1");
//            return 1;
//        });
//        CompletableFuture<CompletableFuture<Integer>> future2 = future1.thenApply((r)->CompletableFuture.supplyAsync(()->r+10));
//        System.out.println(future2.join().join());
//
//        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 1");
//            return 1;
//        });
//        CompletableFuture<Integer> future4 = future3.thenCompose((r)->CompletableFuture.supplyAsync(()->r+10));
//        System.out.println(future4.join());


        //whenComplete
//        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
//            System.out.println("compute 1");
//            throw new NullPointerException();
////            return 1;
//        });
//        CompletableFuture future2 = future1.whenComplete((r, e)->{
//            if(e != null){
//                System.out.println("compute failed!");
//            } else {
//                System.out.println("received result is " + r);
//            }
//        });
//        System.out.println("result: " + future2.join());
//

        // handle
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("compute 1");
            throw new NullPointerException();
//            return 1;
        });
        CompletableFuture<Integer> future2 = future1.handle((r, e)->{
            if(e != null){
                System.out.println("compute failed!");
                return r;
            } else {
                System.out.println("received result is " + r);
                return r + 10;
            }
        });
        System.out.println("result: " + future2.join());




    }



}
