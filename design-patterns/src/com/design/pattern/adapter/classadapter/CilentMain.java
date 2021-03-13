package com.design.pattern.adapter.classadapter;
/**
 * Main方法
 */
public class CilentMain {
    public static void main(String[] args) {
        System.out.println("类适配器模式");
        Phone phone = new Phone();
        // 这里给的适配器
        phone.charging(new VoltageAdapter());
    }
}
