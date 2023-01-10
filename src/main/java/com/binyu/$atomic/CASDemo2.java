package com.binyu.$atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.cas
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 18:08
 * @Description:
 */
public class CASDemo2 {

    public static void main(String[] args) {
        // ABA问题
        AtomicInteger atomicInteger=new AtomicInteger (2020  );
        // ====捣乱的线程====
        System.out.println ( atomicInteger.compareAndSet ( 2020, 2021 ) );
        System.out.println (atomicInteger.get ());
        System.out.println ( atomicInteger.compareAndSet ( 2021, 2020 ) );
        System.out.println (atomicInteger.get ());
        // ====期望的线程====
        System.out.println ( atomicInteger.compareAndSet ( 2020, 2021 ) );
        System.out.println (atomicInteger.get ());
        System.out.println(atomicInteger.getAndIncrement());
    }
}
