package com.design.pattern.singleton.type5;

/**
 * 懒汉式(线程安全，同步代码块) 
 * 错的！！！
 */
public class SingleTonTest05 {

}

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}