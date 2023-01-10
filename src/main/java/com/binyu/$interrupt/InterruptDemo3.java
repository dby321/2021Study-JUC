package com.binyu.$interrupt;

public class InterruptDemo3 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());//main	false
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());//main	false
        Thread.currentThread().interrupt();
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());//main	true
        System.out.println(Thread.currentThread().getName()+"\t"+Thread.interrupted());//main	false
    }
}
