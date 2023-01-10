package com.binyu.$volatile;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.tvolatile
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 16:47
 * @Description:
 */
// 不保证原子性
public class VolatileTest2 {
    private static volatile int num=0;

    public static void add(){
        num++;
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
