package com.binyu.$synchronized;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.lock
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 19:08
 * @Description:
 */
public class ChongRuLockDemo {
    public static void main(String[] args) {
        Phone phone =new Phone ();
        new Thread(()->{
            phone.sms ();
        },"A").start ();
        new Thread(()->{
            phone.sms ();
        },"B").start ();
    }
}
class Phone{
    public synchronized void sms(){
        System.out.println (Thread.currentThread ().getName ()+"sms");
        call();
    }
    public synchronized void call(){
        System.out.println (Thread.currentThread ().getName ()+"call");
    }
}
