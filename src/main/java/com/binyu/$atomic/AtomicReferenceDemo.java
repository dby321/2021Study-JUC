package com.binyu.$atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

@Data
@AllArgsConstructor
class User{
    private String id;
    private String name;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference=new AtomicReference<User>();
        User dby = new User("123", "dby");
        User other=new User("345","other");
        atomicReference.set(dby);
        System.out.println(atomicReference.compareAndSet(dby, other));
        System.out.println(atomicReference.get());
        System.out.println(atomicReference.compareAndSet(dby, other));
        System.out.println(atomicReference.get());
    }
}
