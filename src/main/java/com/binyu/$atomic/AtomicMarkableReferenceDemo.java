package com.binyu.$atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

public class AtomicMarkableReferenceDemo {
    static AtomicMarkableReference markableReference=new AtomicMarkableReference(100,false);
    public static void main(String[] args) {
        new Thread(()->{
            boolean marked=markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(100, 1000, marked, !marked);
            System.out.println(b);
        },"A").start();
        new Thread(()->{
            boolean marked=markableReference.isMarked();
            System.out.println(Thread.currentThread().getName()+"\t"+marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(b);
        },"B").start();
    }
}
