package com.binyu.$atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber {
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addOne() {
        atomicInteger.getAndIncrement();
    }
}

public class AtomicIntegerDemo {
    public static void main(String[] args) throws InterruptedException {
        MyNumber myNumber = new MyNumber();
        CountDownLatch countDownLatch = new CountDownLatch(50);
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        myNumber.addOne();
                    }
                } finally {
                    countDownLatch.countDown();
                }

            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t" + myNumber.atomicInteger.get());
    }
}
