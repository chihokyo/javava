package com.design.pattern.singleton.type3;

/**
 * 懒汉式(线程不安全)
 * 只能单线程使用，多线程出现的就是false
 */
public class SingleTonTest03 {
    public static void main(String[] args) {
        System.out.println("******测试-懒汉式(线程不安全)******");
        Singleton instance = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance == instance2); // false
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
    }
}

class Singleton {
    // 静态的实例
    private static Singleton instance;
    private Singleton() {}
    // 提供一个公共方法 
    // 使用这个方法的时候，才回去创建instance
    // 不同于上面的饿汉是类加载就创建了
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
