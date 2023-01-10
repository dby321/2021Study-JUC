package com.binyu.$volatile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.tvolatile
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 16:47
 * @Description:
 */

public class VolatileTest3 {
    private static volatile AtomicInteger num=new AtomicInteger (  );

    public static void add(){
        num.getAndIncrement ();
    }
    public static void main(String[] args) {
        for(int i=1;i<=20;i++){
            new Thread ( ()->{
                for(int j=0;j<1000;j++){
                    add();
                }
            } ).start ();
        }
        while(Thread.activeCount ()>2){
            Thread.yield ();
        }
        System.out.println (Thread.currentThread ().getName ()+" "+num);
    }
}
