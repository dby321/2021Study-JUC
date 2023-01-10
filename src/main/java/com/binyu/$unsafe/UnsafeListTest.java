package com.binyu.$unsafe;

import java.util.ArrayList;
import java.util.UUID;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.unsafe
 * @Author: Dong Binyu
 * @CreateTime: 2022-04-23 14:43
 * @Description:
 */
public class UnsafeListTest {
    public static void main(String[] args) {
        ArrayList<String> list=new ArrayList<> ();
        for(int i=0;i<30;i++){
            new Thread(()->{
              list.add( UUID.randomUUID ().toString ().substring ( 0,8 ));
              System.out.println ( list );
            },String.valueOf(i)).start ();
        }
    }
}
