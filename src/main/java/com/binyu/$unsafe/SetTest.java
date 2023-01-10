package com.binyu.$unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.unsafe
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 17:52
 * @Description:
 */
// ConcurrentModificationException并发修改异常
public class SetTest {
    public static void main(String[] args) {
//        Set<String> set = Collections.synchronizedSet ( new HashSet<> () );
        CopyOnWriteArraySet<String> set = new CopyOnWriteArraySet<> ();
        for(int i=1;i<=30;i++){
            new Thread(()->{
                set.add ( UUID.randomUUID ().toString ().substring ( 0,5 ) );
                System.out.println (set);
            },String.valueOf ( i )).start ();

        }
    }
}
