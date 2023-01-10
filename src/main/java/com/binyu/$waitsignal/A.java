package com.binyu.$waitsignal;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.pc
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 12:02
 * @Description:
 */
public class A {
    public static void main(String[] args) {
        Data data=new Data ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data.increment ();
                } catch  (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"A").start ();
        new Thread(()->{
            for(int i=0;i<10;i++) {
                try {
                    data.decrement ();
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            } },"B").start ();
    }
}
// 判断等待，业务，通知
class Data{
    private int num=0;
    public synchronized void increment() throws InterruptedException {
        while(num!=0){
            // 等待
            this.wait ();
        }
        num++;
        System.out.println (Thread.currentThread ().getName ()+"=>"+num);
        // 通知其他线程，我+1完毕了
        this.notifyAll ();
    }
    public synchronized void decrement() throws InterruptedException {
        while(num==0){
            //等待
        this.wait ();
        }
        num--;
        System.out.println (Thread.currentThread ().getName ()+"=>"+num);
        // 通知其他线程，我-1完毕了
        this.notifyAll ();
    }
}