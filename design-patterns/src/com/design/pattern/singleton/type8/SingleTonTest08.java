package com.design.pattern.singleton.type8;
/**
 * 枚举类实现
 */
public class SingleTonTest08 {
    public static void main(String[] args) {
        System.out.println("******测试-枚举类实现******");
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
        instance.method();
        instance2.method();
    }
}

// 使用枚举可以实现单例，这个是Effective java 作者推荐
enum Singleton {
    // 属性 因为在enum只有1个属性 保证是单例
    INSTANCE;
    public void method() {
        System.out.println("OK");
    }
}
