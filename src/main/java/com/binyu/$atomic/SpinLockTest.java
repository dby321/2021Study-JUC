package com.binyu.$atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.lock
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 19:24
 * @Description:
 */
public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {
        // 底层使用自旋锁
        SpinLock spinLock=new SpinLock ();
        new Thread(()->{
            spinLock.myLock ();
            try {
                TimeUnit.SECONDS.sleep ( 3 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            } finally {
                spinLock.myUnLock ();
            }
        },"A").start ();
        TimeUnit.SECONDS.sleep ( 1 );
        new Thread(()->{
        spinLock.myLock ();
            try {
                TimeUnit.SECONDS.sleep ( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            } finally {
                spinLock.myUnLock ();
            }
        },"B").start ();

    }
}

class SpinLock {
    AtomicReference<Thread> atomicReference=new AtomicReference<> (  );
    public void myLock(){
        Thread thread=Thread.currentThread ();
        System.out.println (Thread.currentThread ().getName ()+"--mylock");
        while(!atomicReference.compareAndSet ( null,thread ))   {

        }
    }
    public void myUnLock(){
        Thread thread=Thread.currentThread ();
        System.out.println (Thread.currentThread ().getName ()+"--myUnlock");
        atomicReference.compareAndSet ( thread,null );
    }

}
