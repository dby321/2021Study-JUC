package com.binyu.$interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;


public class InterruptDemo {
    static volatile boolean isStop =false;
    static AtomicBoolean atomicBoolean=new AtomicBoolean(false);
    public static void main(String[] args) {
        m1_volatile();
        m2_AtomicBoolean();
        m3_InterruptApi();
    }

    /**
     * volatile实现线程中断
     */
    private static void m1_volatile() {
        new Thread(()->{
            while(true){
                if(isStop){
                    System.out.println(Thread.currentThread().getName()+"\t isStop被修改为true,程序停止");
                    break;
                }
                System.out.println("t1----hello volatile");
            }
        },"t1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            isStop=true;
        },"t2").start();
    }

    /**
     * AtomicBoolean实现线程中断
     */
    private static void m2_AtomicBoolean() {
        new Thread(()->{
            while(true){
                if(atomicBoolean.get()){
                    System.out.println(Thread.currentThread().getName()+"\t atomicBoolean被修改为true,程序停止");
                    break;
                }
                System.out.println("t1----hello atomicBoolean");
            }
        },"t1").start();
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            atomicBoolean.set(true);
        },"t2").start();
    }

    /**
     * interrupt api实现线程中断
     */
    private static void m3_InterruptApi() {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isInterrupted()被修改为true,程序停止");
                    break;
                }
                System.out.println("t1----hello interrupt api");
            }
        }, "t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Thread t2 = new Thread(() -> {
            t1.interrupt();
        }, "t2");
        t2.start();
    }
}
