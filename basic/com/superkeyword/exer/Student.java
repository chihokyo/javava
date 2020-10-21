package com.superkeyword.exer;

public class Student extends Person {
    String major;
    int id = 100; // 学号

    public Student() {

    }

    public Student(String major) {
        this.major = major;
    }

    public Student(String name, int age, String major) {
        // 一旦父类如果把属性设置成了private 就无法这样进行调用
        // this.name = name;
        // this.age = age;
        super(name, age); // 这样就是使用形参，调用指定参数列表的父类构造器
        this.major = major;
    }

    @Override
    public void eat() {
        System.out.println("student eat");
    }

    public void study() {
        System.out.println("student study");
        eat();
        this.eat(); // 显示调用
        super.eat();

    }

    public void show() {
        // 相当于省略了this，
        // System.out.println("name" + this.name + "age" + this.age);

        System.out.println("name " + name + " age" + age);
        System.out.println("name " + this.name + " age" + super.age);
        System.out.println("stuId " + this.id + " perId" + super.id);

    }

}
