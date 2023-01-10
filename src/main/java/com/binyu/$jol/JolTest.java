package com.binyu.$jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JolTest {
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println(VM.current().objectAlignment());
        System.out.println("------------------");
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}
class Customer{
    int id;
    String name;
    boolean sex;
}
