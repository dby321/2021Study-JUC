package com.binyu.$accessors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

public class AccessorsDemo {
    public static void main(String[] args) {
        Student student = new Student();
        student.setAge(12).setId("123").setName("dongbinyu");
    }
}
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class Student{
    private String id;
    private String name;
    private Integer age;
}
