package com.design.pattern.singleton.type2;

/**
 * 饿汉式(静态代码块)
 * 其实本质就是把静态常量写在了静态代码块里面 本质没区别
 */
public class SingleTonTest02 {
    public static void main(String[] args) {
        System.out.println("******测试-饿汉式(静态代码块)******");
        SingleTon instance = SingleTon.getInstance();
        SingleTon instance2 = SingleTon.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}


class SingleTon {
    private SingleTon(){

    }
    private static SingleTon instance;
    // 区别于上一个静态常量 这里使用的是静态代码块
    // 类加载的时候自动加载，所以会消耗一点资源
    static {
        instance = new SingleTon();
    }
    public static SingleTon getInstance() {
        return instance;
    }
}