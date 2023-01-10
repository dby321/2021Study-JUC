package com.binyu.$unsafe;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.unsafe
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 17:52
 * @Description:
 */
// ConcurrentModificationException并发修改异常
public class ListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<> ();
        // 并发下ArrayList不安全
        // 解决方案1：Vector
        // 解决方案2：Collections.synchronizedList()
        // 解决方案3：JUC CopyOnWriteArrayList
        for(int i=1;i<=10;i++){
            new Thread(()->{
                list.add ( UUID.randomUUID ().toString ().substring ( 0,5 ) );
                System.out.println (list);
            },String.valueOf ( i )).start ();
        }
    }
}
