package com.binyu.$jol;

import org.openjdk.jol.info.ClassLayout;

public class BiasedLockTest {
    public static void main(String[] args) {
        Object o = new Object();
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
