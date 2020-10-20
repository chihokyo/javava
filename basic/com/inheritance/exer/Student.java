package com.inheritance.exer;

public class Student extends Person{

    // String name;
    // int age;
    // String gender;
    int grade;

    public Student() {
        
    }

    public Student(String name, int age, String gender, int grade) {
        this.name = name;
        // this.age = age;
        this.gender = gender;
        this.grade = grade;
    }

    // public void eat() {
    //     System.out.println("eat");
    // }
    
    // public void run() {
    //     System.out.println("run");
    // }

    // public void sleep() {
    //     System.out.println("sleep");
    // }

    public void study() {
        System.out.println("study");
    }

    public void show() {
        System.out.println("name" + name + "age" + getAge());
    }
}
