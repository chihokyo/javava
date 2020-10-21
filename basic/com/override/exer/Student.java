package com.override.exer;

public class Student extends Person{
    
    String major;

    public Student() {
        
    }
    public Student( String major) {
        this.major = major;
    }

    public void study() {
        System.out.println("study at " + major);
    }


    // 重写父类
    public void eat() {
        System.out.println("study eat");
    }

}
