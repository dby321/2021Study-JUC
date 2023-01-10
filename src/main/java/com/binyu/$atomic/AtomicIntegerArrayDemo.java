package com.binyu.$atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray=new AtomicIntegerArray(5);
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.getAndIncrement(0));
        System.out.println(atomicIntegerArray.get(0));
    }
}
