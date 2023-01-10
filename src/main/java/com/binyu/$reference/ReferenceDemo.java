package com.binyu.$reference;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class MyObject{
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize invoke....");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
//        strongReference();
//        softReference();
//        weakReference();
        phantomReference();
    }

    private static void phantomReference() {
        ReferenceQueue<MyObject> referenceQueue=new ReferenceQueue<>();
        PhantomReference<MyObject> myObjectPhantomReference=new PhantomReference<>(new MyObject(),referenceQueue);
        System.out.println(myObjectPhantomReference.get());//null
        List<byte[]> list=new ArrayList<>();
        new Thread(()->{
            while(true){
                list.add(new byte[1*1024*1024]);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(myObjectPhantomReference.get()+"\t"+"list add ok");
                } catch (InterruptedException e) {
                   e.printStackTrace();
                }
            }
        },"T1").start();
        new Thread(()->{
            while(true){
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if(reference!=null){
                    System.out.println("有虚对象加入了引用队列");
                    break;
                }
            }
        },"T2").start();
    }

    private static void weakReference() {
        WeakReference<MyObject> myObjectWeakReference=new WeakReference<>(new MyObject());
        System.out.println(myObjectWeakReference.get());
        System.gc();
        System.out.println(myObjectWeakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> myObjectSoftReference = new SoftReference<>(new MyObject());
        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after内存够用 "+myObjectSoftReference.get());
        try {
            byte[] bytes = new byte[10 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after内存不够用"+myObjectSoftReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject=new MyObject();
        System.out.println("gc before"+myObject);
        myObject=null;
        System.gc();
        System.out.println("gc after"+myObject);
    }

}
