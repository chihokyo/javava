package com.designpattern.exer;

/**
 * 单例设计模式2 
 * 懒汉 什么时候用什么时候new
 */
public class SingleTonTest2 {
    public static void main(String[] args) {
        Bank2 b1 = Bank2.getInstance();
        Bank2 b2 = Bank2.getInstance();
        System.out.println(b1 == b2); // true
    }
}

class Bank2 {

    // 1 私有化里的构造器 → 保证只有这个类内部才能进行new
    private Bank2() {

    }

    // 2 声明当前类对象，没有初始化 → 要求这个属性也是static
    private static Bank2 instance = null;

    // 3，声明 public static 返回当前类的对象方法
    public static Bank2 getInstance() {
        // instance = new Bank2(); 这样每次都是全新的一个对象，就违反了单例模式的规定
        // 所以在这个地方做了一个判断，如果你是null，那么就new一个
        // 如果不是null 那么就直接返回这个实例
        if (instance == null) {
            instance = new Bank2();
        }
        return instance;
    }
}