package com.binyu.$atomic;

import lombok.Data;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
@Data
class ClickNumber{
    private int number=0;
    public synchronized void clickBySynchronized(){
        number++;
    }
    AtomicLong atomicLong=new AtomicLong(0);
    public void clickByAtomicLong(){
        atomicLong.getAndIncrement();
    }
    LongAdder longAdder=new LongAdder();
    public void clickByLongAdder(){
        longAdder.increment();
    }
    LongAccumulator longAccumulator=new LongAccumulator((x,y)->x+y,0);
    public void clickByLongAccumulator(){
        longAccumulator.accumulate(1);
    }
}
public class LongAdderDemo {
    public static final int _100w=1000000;
    public static final int threadNumber=50;
    public static void main(String[] args) throws InterruptedException {
        testApi();
        ClickNumber clickNumber=new ClickNumber();
        CountDownLatch countDownLatch1=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3=new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4=new CountDownLatch(threadNumber);
        testClickBySynchronized(clickNumber, countDownLatch1);
        testClickByAtomicLong(clickNumber,countDownLatch2);
        testClickByLongAdder(clickNumber,countDownLatch3);
        testClickByLongAccumulator(clickNumber,countDownLatch4);
    }

    private static void testClickBySynchronized(ClickNumber clickNumber, CountDownLatch countDownLatch1) throws InterruptedException {
        long startTime1=System.currentTimeMillis();
        for(int i=0;i<threadNumber;i++){
            new Thread(()->{
                try {
                    for(int j=0;j<_100w;j++){
                        clickNumber.clickBySynchronized();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        long endTime1=System.currentTimeMillis();
        System.out.println("clickBySynchronized："+(endTime1-startTime1)+"\t"+clickNumber.getNumber());
    }
    private static void testClickByAtomicLong(ClickNumber clickNumber, CountDownLatch countDownLatch1) throws InterruptedException {
        long startTime1=System.currentTimeMillis();
        for(int i=0;i<threadNumber;i++){
            new Thread(()->{
                try {
                    for(int j=0;j<_100w;j++){
                        clickNumber.clickByAtomicLong();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        long endTime1=System.currentTimeMillis();
        System.out.println("clickByAtomicLong："+(endTime1-startTime1)+"\t"+clickNumber.getAtomicLong().get());
    }
    private static void testClickByLongAdder(ClickNumber clickNumber, CountDownLatch countDownLatch1) throws InterruptedException {
        long startTime1=System.currentTimeMillis();
        for(int i=0;i<threadNumber;i++){
            new Thread(()->{
                try {
                    for(int j=0;j<_100w;j++){
                        clickNumber.clickByLongAdder();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        long endTime1=System.currentTimeMillis();
        System.out.println("clickByLongAdder："+(endTime1-startTime1)+"\t"+clickNumber.getLongAdder().sum());
    }
    private static void testClickByLongAccumulator(ClickNumber clickNumber, CountDownLatch countDownLatch1) throws InterruptedException {
        long startTime1=System.currentTimeMillis();
        for(int i=0;i<threadNumber;i++){
            new Thread(()->{
                try {
                    for(int j=0;j<_100w;j++){
                        clickNumber.clickByLongAccumulator();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            },String.valueOf(i)).start();
        }
        countDownLatch1.await();
        long endTime1=System.currentTimeMillis();
        System.out.println("clickByLongAccumulator："+(endTime1-startTime1)+"\t"+clickNumber.getLongAccumulator().get());
    }

    private static void testApi() {
        LongAdder longAdder=new LongAdder();
        longAdder.add(1);
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator=new LongAccumulator((x,y)->{
            return x+y;
        },0);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);
        System.out.println(longAccumulator.get());
    }
}
