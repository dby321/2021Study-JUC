package com.binyu.$atomic;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Data
class BankAccount {
    private String name = "CCB";
    private volatile int money = 0;
    private int money2 = 0;
    private AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public void addM1() {
        fieldUpdater.getAndIncrement(this);
    }

    public synchronized void addM2() {
        money2++;
    }
}

public class AtomicIntegerFieldUpdaterDemo {
    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
    }

    private static void m1() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.addM1();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(bankAccount.getMoney());
        long endTime = System.currentTimeMillis();
        System.out.println("耗时毫秒数m1：" + (endTime - startTime));
    }

    private static void m2() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < 1000; j++) {
                        bankAccount.addM2();
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(bankAccount.getMoney2());
        long endTime = System.currentTimeMillis();
        System.out.println("耗时毫秒数m2：" + (endTime - startTime));
    }
}
