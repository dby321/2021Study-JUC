package com.binyu.$singlemodel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.single
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 17:23
 * @Description:
 */
public class LazyMan2 {
    private LazyMan2(){
        synchronized (LazyMan.class){
            if(lazyMan!=null){
                throw new RuntimeException ( "不要使用反射破坏单例" );
            }
            System.out.println (Thread.currentThread ().getName ()+"ok");
        }

    }
    private volatile static  LazyMan2 lazyMan;
    public static LazyMan2 getInstance(){
        if(lazyMan==null){
            synchronized (LazyMan2.class){
                if(lazyMan==null){
                    lazyMan=new LazyMan2 ();
                    /*
                    * 1. 分配内存空间
                    * 2. 执行构造方法，初始化对象
                    * 3. 把对象指向这个空间
                    * 123 正常
                    * 132 A
                    *     B //此时LazyMan还没有完成构造
                    * */
                }
            }
        }
        return lazyMan;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for(int i=0;i<10;i++){
            new Thread(()->{
                LazyMan2.getInstance ();
            }).start ();
        }
        System.out.println ("---");
//        LazyMan2 instance1=LazyMan2.getInstance ();
        Constructor<LazyMan2> declaredConstructor = LazyMan2.class.getDeclaredConstructor ( null );
        declaredConstructor.setAccessible ( true );
        LazyMan2 instance2=declaredConstructor.newInstance (  );
        LazyMan2 instance3=declaredConstructor.newInstance (  );
//        System.out.println (instance1);
        System.out.println (instance2);
        System.out.println (instance3);
    }
}
