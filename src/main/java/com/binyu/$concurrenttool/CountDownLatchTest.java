package com.binyu.$concurrenttool;

import java.util.concurrent.CountDownLatch;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.fuzhu
 * @Author: Dong Binyu
 * @CreateTime: 2022-04-23 18:53
 * @Description:
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch (  6);
        for(int i=1;i<=6;i++){
            new Thread(()-> {
                System.out.println ( Thread.currentThread ().getName () + "号同学离开了" );
                countDownLatch.countDown ();
            },String.valueOf(i)).start ();
        }
        countDownLatch.await ();
        System.out.println (Thread.currentThread ().getName ()+"班长锁门");
    }
}
