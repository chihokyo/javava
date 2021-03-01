package com.design.pattern.singleton.type1;

/**
 * 饿汉式(静态常量)
 * 1. 构造器私有化（防止new）
 * 2. 类的内部创建对象
 * 3. 暴露一个静态公共方法（为了获得实例对象）
 */
public class SingleTonTest01 {
    public static void main(String[] args) {
        System.out.println("******测试-饿汉式(静态常量)******");
        SingleTon instance = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class SingleTon {

    // 1. 构造器私有化（防止new）
    private SingleTon() {

    }

    // 2. 类的内部创建对象
    private final static SingleTon instance = new SingleTon();

    // 3. 暴露一个静态公共方法（为了获得实例对象）
    public static SingleTon getInstance() {
        return instance;
    }
}
