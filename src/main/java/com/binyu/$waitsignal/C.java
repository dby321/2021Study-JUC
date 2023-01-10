package com.binyu.$waitsignal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.pc
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 12:02
 * @Description:
 */
public class C {
    public static void main(String[] args) {
        Data3 data3=new Data3();

        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data3.printA ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        },"A").start ();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data3.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        },"B").start ();
        new Thread(()->{
            for(int i=0;i<10;i++){
                try {
                    data3.printC ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        },"C").start ();

    }
}

// 判断等待，业务，通知
class Data3{
    private int num=0;
    Lock lock=new ReentrantLock (  );
    Condition condition1 = lock.newCondition ();
    Condition condition2 = lock.newCondition ();
    Condition condition3 = lock.newCondition ();
    private int number=1;//1A,2B,3C
    public  void printA() throws InterruptedException {
        lock.lock ();
        try {
            while(number!=1){
                condition1.await ();
            }
            System.out.println (Thread.currentThread ().getName ()+"=>A");
            number=2;
            condition2.signal ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }

    }
    public  void printB() throws InterruptedException {
        lock.lock ();
        try {
            while(number!=2){
                condition2.await ();
            }
            System.out.println (Thread.currentThread ().getName ()+"=>B");
            number=3;
            condition3.signal ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }

    }
    public  void printC() throws InterruptedException {
        lock.lock ();
        try {
            while(number!=3){
                condition3.await ();
            }
            System.out.println (Thread.currentThread ().getName ()+"=>C");
            number=1;
            condition1.signal ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }

    }
}