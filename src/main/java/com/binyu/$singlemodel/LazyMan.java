package com.binyu.$singlemodel;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.single
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 17:23
 * @Description:
 */
public class LazyMan {
    private LazyMan(){
        System.out.println (Thread.currentThread ().getName ()+"ok");
    }
    private static LazyMan lazyMan;
    public static LazyMan getInstance(){
        if(lazyMan==null){
            lazyMan=new LazyMan ();
        }
        return lazyMan;
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{
                LazyMan.getInstance ();
            }).start ();
        }
    }
}
