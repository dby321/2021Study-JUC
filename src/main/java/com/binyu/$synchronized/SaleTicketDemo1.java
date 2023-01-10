package com.binyu.$synchronized;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-24 11:28
 * @Description:
 */
// 线程就是一个单独的资源类，没有任何附属的操作
public class SaleTicketDemo1 {
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
class Ticket {
    private int number = 50;

    public synchronized void sale() {
        if (number > 0) {
            System.out.println ( Thread.currentThread ().getName () + "卖出了第" + (number--) + "票，剩余" + number );
        }
    }
}