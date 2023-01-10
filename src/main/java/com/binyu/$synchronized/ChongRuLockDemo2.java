package com.binyu.$synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.lock
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 19:12
 * @Description:
 */
public class ChongRuLockDemo2 {
    public static void main(String[] args) {
        Phone2 phone =new Phone2 ();
        new Thread(()->{
            phone.sms ();
        },"A").start ();
        new Thread(()->{
            phone.sms ();
        },"B").start ();
    }
}
class Phone2{
    Lock lock=new ReentrantLock (  );
    public  void sms(){
        lock.lock ();
        try {
            System.out.println (Thread.currentThread ().getName ()+"sms");
            call();
        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }
    }
    public synchronized void call(){
        lock.lock ();
        try {
            System.out.println (Thread.currentThread ().getName ()+"call");

        } catch (Exception e) {
            e.printStackTrace ();
        } finally {
            lock.unlock ();
        }
    }
}

