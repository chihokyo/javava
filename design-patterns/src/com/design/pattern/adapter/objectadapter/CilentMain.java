package com.design.pattern.adapter.objectadapter;

/**
 * 对象适配模式 
 * 其实本质就是比类来说使用对象组合替代了那张继承
 */
public class CilentMain {
    public static void main(String[] args) {
        System.out.println("对象适配器模式");
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage220V()));
    }
}
