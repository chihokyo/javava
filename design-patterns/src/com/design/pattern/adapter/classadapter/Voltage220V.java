package com.design.pattern.adapter.classadapter;

/**
 * 被适配的类
 */
public class Voltage220V {
    public int output220V() {
        int src = 220;
        System.out.println("这里电压" + src + "V");
        return src;
    }
}
