package com.binyu;


public class LockClearDemo {
    static Object objectLock=new Object();
    public void m1(){
        Object o=new Object();
        synchronized (o){
            System.out.println(o.hashCode()+"\t"+objectLock.hashCode());
        }
    }

    public static void main(String[] args) {
        LockClearDemo lockClearDemo = new LockClearDemo();
        for(int i=0;i<10;i++){
            new Thread(()->{
                lockClearDemo.m1();
            },String.valueOf(i)).start();
        }
    }
}
