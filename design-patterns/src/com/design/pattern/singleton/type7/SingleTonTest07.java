package com.design.pattern.singleton.type7;

/**
 * 静态内部类
 * 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 */
public class SingleTonTest07 {
    public static void main(String[] args) {
        System.out.println("******测试-使用静态内部类完成单例模式******");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton {
    private static volatile Singleton instance;

    // 构造器私有化
    private Singleton() {

    }

    // 其实这里就是一个静态内部类，该类有一个属性 Singleton
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    // 提供一个静态的公有方法，直接返回 SingletonInstance.INSTANCE
    public static synchronized Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}