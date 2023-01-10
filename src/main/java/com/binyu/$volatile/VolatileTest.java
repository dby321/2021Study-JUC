package com.binyu.$volatile;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.tvolatile
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 16:42
 * @Description:
 */
// 不加volatile会导致死循环
public class VolatileTest {
    private  volatile static int num=0;
    public static void main(String[] args) {
        new Thread(()->{
            while(num==0){ }
        }).start ();
        try {
            TimeUnit.SECONDS.sleep ( 2 );
        } catch (InterruptedException e) {
            e.printStackTrace ();
        }
        num=2;
    }
}
