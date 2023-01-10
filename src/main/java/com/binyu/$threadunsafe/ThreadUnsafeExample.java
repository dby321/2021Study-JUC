package com.binyu.$threadunsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu2._01threadUnsafe
 * @Author: Dong Binyu
 * @CreateTime: 2022-04-30 10:17
 * @Description: 结果总是小于1000
 */
public class ThreadUnsafeExample {
    private int cnt=0;
    public void add(){
        cnt++;
    }
    public int get(){
        return cnt;
    }

    @SuppressWarnings("AlibabaThreadPoolCreation")
    public static void main(String[] args) throws InterruptedException {
        final int threadSize=1000;
        ThreadUnsafeExample threadUnsafeExample=new ThreadUnsafeExample ();
        final CountDownLatch countDownLatch=new CountDownLatch ( threadSize );
        ExecutorService executorService= Executors.newCachedThreadPool ();
        for(int i=0;i<threadSize;i++){
            executorService.execute ( ()->{
                threadUnsafeExample.add ();
                countDownLatch.countDown ();
            } );
        }
        countDownLatch.await ();
        executorService.shutdown ();
        System.out.println (threadUnsafeExample.get ());
    }
}
