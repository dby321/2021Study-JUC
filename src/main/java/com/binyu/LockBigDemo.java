package com.binyu;

public class LockBigDemo {
    static Object objectLock=new Object();

    public static void main(String[] args) {
        new Thread(()->{
            synchronized (objectLock){
                System.out.println("111");
            }
            synchronized (objectLock){
                System.out.println("222");
            }
            synchronized (objectLock){
                System.out.println("333");
            }synchronized (objectLock){
                System.out.println("444");
            }
        }).start();
    }
}
