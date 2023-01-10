package com.binyu.$atomic;

import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

@Data
class MyCar{
    private volatile Boolean initOrNot=Boolean.FALSE;
    private AtomicReferenceFieldUpdater<MyCar,Boolean> fieldUpdater=AtomicReferenceFieldUpdater.newUpdater(MyCar.class,Boolean.class,"initOrNot");
    public void init(){
        if(fieldUpdater.compareAndSet(this,Boolean.FALSE,Boolean.TRUE)){
            System.out.println(Thread.currentThread().getName()+"\t start init");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+"\t end init");
        }else{
            System.out.println(Thread.currentThread().getName()+"\t 已经有线程初始化了");
        }
    }
}
public class AtomicReferenceFieldUpdaterDemo {
    public static void main(String[] args) {
        MyCar myCar = new MyCar();
        for(int i=0;i<5;i++){
            new Thread(()->{
                myCar.init();
            },String.valueOf(i)).start();
        }

    }
}
