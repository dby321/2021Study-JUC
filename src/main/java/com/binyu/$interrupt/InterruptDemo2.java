package com.binyu.$interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true,程序停止");
                    break;
                }
                try {
                    // 此时中断状态被清除，并抛出异常
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    //----- 重新给当前线程设置中断标志 -----
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
                System.out.println("t1----hello interrupt api");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t2 = new Thread(() -> {
            t1.interrupt();
        }, "t2");
        t2.start();
    }
}
