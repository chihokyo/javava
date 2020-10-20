package com.inheritance.exer;

public class Person {

    String name;
    private int age;
    String gender;

    public Person() {

    }

    public Person(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void eat() {
        System.out.println("eat");
    }

    public void run() {
        System.out.println("run");
    }

    public void sleep() {
        System.out.println("sleep");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
