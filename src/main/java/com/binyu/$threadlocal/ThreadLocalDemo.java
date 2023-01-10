package com.binyu.$threadlocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class House{
    int saleCount=0;
    public synchronized void saleHouse(){
        saleCount++;
    }
//    ThreadLocal<Integer> saleVolume=new ThreadLocal<Integer>(){
//        @Override
//        protected Integer initialValue(){
//            return 0;
//        }
//    };
    ThreadLocal<Integer> saleVolume=ThreadLocal.withInitial(()->0);
    public void saleVolumeByThreadLocal(){
        saleVolume.set(1+ saleVolume.get());
    }
}
public class ThreadLocalDemo {
    public static void main(String[] args) throws InterruptedException {
        House house = new House();
        for(int i=0;i<5;i++){
            new Thread(()->{
                int size = new Random().nextInt(5) + 1;
                System.out.println(size);
                try {
                    for(int j=1;j<=size;j++){
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName()+"\t"+house.saleVolume.get());
                } finally {
                    house.saleVolume.remove();
                }
            },String.valueOf(i)).start();
        }
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println(house.saleCount);
        System.out.println(house.saleVolume.get());
    }
}
