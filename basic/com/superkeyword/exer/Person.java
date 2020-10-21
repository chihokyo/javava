package com.superkeyword.exer;

public class Person {

    String name;
    int age;
    int id = 200; // 人号

    public Person() {

    }

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this(name);// 相当于使用上面的构造器
        this.age = age;
    }

    public void eat() {
        System.out.println("person eat");
    }

    public void sleep() {
        System.out.println("person sleep");
    }
}
