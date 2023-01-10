package com.binyu.$atomic;

import java.util.concurrent.atomic.AtomicReference;

public class SpinLockDemo {
     AtomicReference<Thread> atomicReference=new AtomicReference<>();
     public void lock(){
         Thread thread=Thread.currentThread();
         System.out.println(Thread.currentThread().getName()+"\t come in");
         while(!atomicReference.compareAndSet(null,thread)){

         }
     }
     public void unlock(){
         Thread thread=Thread.currentThread();
         atomicReference.compareAndSet(thread,null);
         System.out.println(Thread.currentThread().getName()+"\t task over unlock");
     }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.lock();
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            spinLockDemo.unlock();
        },"A").start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            spinLockDemo.lock();
            spinLockDemo.unlock();
        },"B").start();
    }
}
