package com.binyu.$future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.future
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 16:04
 * @Description:
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture=CompletableFuture.supplyAsync ( ()->{
            System.out.println (Thread.currentThread ().getName ()+"supplyAsync");
            int i=10/0;
            return 1024;
        } );
        System.out.println (completableFuture.whenComplete ( (t,u)->{
            System.out.println ("t:"+t);//正确的返回结果
            System.out.println ("u:"+u);// 错误信息
        } ).exceptionally ( e->{
            System.out.println (e.getMessage ());// 错误信息
            return 233;
        } ).get ());
    }
}
