package com.binyu.$atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@AllArgsConstructor
class Book{
    private int id;
    private String name;
}
public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        Book book=new Book(123,"时间简史");
        AtomicStampedReference<Book> atomicStampedReference=new AtomicStampedReference<>(book,1);
        System.out.println(atomicStampedReference.getReference()+"\t"+atomicStampedReference.getStamp());
        Book book2=new Book(456,"mysql实战");
        boolean b = atomicStampedReference.compareAndSet(book, book2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b);
        boolean b1 = atomicStampedReference.compareAndSet(book2, book, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b1);
        boolean b2 = atomicStampedReference.compareAndSet(book, book2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        System.out.println(b2);
        System.out.println(atomicStampedReference.getStamp());
    }
}
