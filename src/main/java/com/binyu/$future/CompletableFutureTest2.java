package com.binyu.$future;

import java.util.concurrent.*;

public class CompletableFutureTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
///        不推荐使用
//        CompletableFuture<String> completableFuture=new CompletableFuture<>();
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());// ForkJoinPool.commonPool-worker-9
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(voidCompletableFuture.get());// null
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        CompletableFuture<Void> voidCompletableFuture2= CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName());// pool-1-thread-1
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },threadPool);
        System.out.println(voidCompletableFuture2.get());// null

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());// pool-1-thread-1
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hello supplyAsync";
        },threadPool);
        System.out.println(completableFuture.get());//hello supplyAsync
        threadPool.shutdown();
    }
}
