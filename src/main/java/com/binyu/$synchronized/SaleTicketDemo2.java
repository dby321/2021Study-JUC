package com.binyu.$synchronized;

import com.binyu.$synchronized.Ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 11:28
 * @Description:
 */
// 线程就是一个单独的资源类，没有任何附属的操作
public class SaleTicketDemo2 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket ();
        new Thread ( () -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale ();
            }
        }, "A" ).start ();
        new Thread ( () -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale ();
            }
        }, "B" ).start ();
        new Thread ( () -> {
            for (int i = 1; i < 60; i++) {
                ticket.sale ();
            }
        }, "C" ).start ();
    }
}

// 资源类 OOP
// synchronized 本质：队列，锁
class Ticket2 {
    private int number = 50;
    Lock lock=new ReentrantLock (  );
    public  void sale() {
        if (number > 0) {
            lock.lock ();
            try {
                System.out.println ( Thread.currentThread ().getName () + "卖出了第" + (number--) + "票，剩余" + number );
            } catch (Exception e) {
                e.printStackTrace ();
            } finally {
                lock.unlock ();
            }
        }
    }
}