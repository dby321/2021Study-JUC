package com.binyu.$atomic;

import sun.misc.Unsafe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.cas
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 18:08
 * @Description:
 */
public class CASDemo {

    public static void main(String[] args) {
        // ABA问题
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<> ( 123, 1 );
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp ();
            System.out.println ("a1="+stamp);
            try {
                TimeUnit.SECONDS.sleep ( 1 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            atomicStampedReference.compareAndSet ( 123,124 ,atomicStampedReference.getStamp (),atomicStampedReference.getStamp ()+1);
            System.out.println ("a2="+atomicStampedReference.getStamp ());
            atomicStampedReference.compareAndSet ( 124,123 ,atomicStampedReference.getStamp (),atomicStampedReference.getStamp ()+1);
            System.out.println ("a3="+atomicStampedReference.getStamp ());
        }).start ();
        new Thread(()->{
            int stamp=atomicStampedReference.getStamp ();
            System.out.println ("b1="+stamp);
            try {
                TimeUnit.SECONDS.sleep ( 2 );
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
            atomicStampedReference.compareAndSet ( 123,125 ,stamp, stamp+1);
            System.out.println ("b2="+atomicStampedReference.getStamp ());

        }).start ();
    }
}
