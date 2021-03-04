package com.design.pattern.singleton.type6;

/**
 * 双重检查 
 * 线程安全;延迟加载;效率较
 */
public class SingleTonTest06 {
    public static void main(String[] args) {
        System.out.println("******测试-双重检查******");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // true
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton {
    // volatile 暂时可以理解成简易版本的 synchronized
    private static volatile Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的公有方法，加入双重检查代码，
    // 解决线程安全问题, 同时解决懒加载问题
    public static synchronized Singleton getInstance() {
        // 第一层判断 第一次实例化之后 第二次走这里就会直接return instance
        // 如果为空就会进去
        if (instance == null) {
            // 线程安全判断
            // 这段代码是同步代码，只能是1个线程进去之后
            // 保证了只能是1个线程在下面的synchronized执行
            // 加入有第2个线程已经进入到了这里 第1个还没有new到
            // 但是下面这段代码是线程安全的，只能保证1个 所以在等待
            synchronized (Singleton.class) {
                // 这时候第1个出去了，第2个等待之后进来了
                // 再一次的进行判断，发现1个实例已经创建了
                // 所以也不会多于的创建新的实例对象
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}