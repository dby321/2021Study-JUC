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
public class B {
    public static void main(String[] args) {
        Data2 data2=new Data2 ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data2.increment ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"A").start ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data2.decrement ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"B").start ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data2.increment ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"C").start ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data2.decrement ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"D").start ();
    }
}

// 判断等待，业务，通知
class Data2{
    private int num=0;
    Lock lock=new ReentrantLock (  );
    Condition condition = lock.newCondition ();
    public  void increment() throws InterruptedException {
        lock.lock ();
        try {
            while(num!=0){
                // 等待
                condition.await ();
            }
            num++;
            System.out.println (Thread.currentThread ().getName ()+"=>"+num);
            // 通知其他线程，我+1完毕了
            condition.signalAll ();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }

    }
    public  void decrement() throws InterruptedException {
        lock.lock ();
        try {
            while(num==0){
                //等待
                condition.await ();
            }
            num--;
            System.out.println (Thread.currentThread ().getName ()+"=>"+num);
            // 通知其他线程，我-1完毕了
            condition.signalAll ();
        } catch (InterruptedException e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }
    }
}