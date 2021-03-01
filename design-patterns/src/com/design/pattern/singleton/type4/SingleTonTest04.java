package com.design.pattern.singleton.type4;

/**
 * 懒汉式(线程安全，同步方法)
 * 使用 synchronized 实现线程安全
 */
public class SingleTonTest04 {
    public static void main(String[] args) {

        System.out.println("******测试-懒汉式(线程安全，同步方法)******");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton {
    private static Singleton instance; 
    private Singleton() {}
    // 提供一个静态的公有方法
    // 加入同步处理的代码，解决线程安全问题 
    // 即懒汉式
    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance; 
    }
}
