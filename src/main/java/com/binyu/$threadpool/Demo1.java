package com.binyu.$threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.pool
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-25 19:49
 * @Description:
 */
public class Demo1 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor ();// 单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool ( 5 );// 固定大小的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool ();// 可伸缩的线程池
        try {
            for(int i=0;i<100;i++){
                threadPool.execute ( ()->{
                    System.out.println (Thread.currentThread ().getName ()+" ok");
                } );
            }
            // 关闭线程池
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            threadPool.shutdown ();
        }

    }
}
