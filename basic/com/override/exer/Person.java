package com.override.exer;

public class Person {
    
    String name;
    int age;

    public Person() {
        
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println("eat");
    }

    public void walk(int distance) {
        System.out.println("walk: " + distance  + " km");
    }

    
}
