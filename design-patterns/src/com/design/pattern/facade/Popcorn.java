package com.design.pattern.facade;

public class Popcorn {
    // 这里使用的是单例模式
    // 使用static 就是类创建就生成
    private static Popcorn instance = new Popcorn();
    public static Popcorn getInstance() {
        return instance;
    }
    public void on() {
        System.out.println("Popcorn on");
    }
    public void off() {
        System.out.println("Popcorn off");
    }
    public void pop() {
        System.out.println("Popcorn pop");
    } 
}
