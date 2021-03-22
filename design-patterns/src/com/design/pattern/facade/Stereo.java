package com.design.pattern.facade;

/**
 * Stereo 类
 * 单独小家电
 */
public class Stereo {
    // 这里使用的是单例模式
    private static Stereo instance = new Stereo();
    public static Stereo getInstance() {
        return instance;
    }
    public void on() {
        System.out.println("Stereo on");
    }
    public void off() {
        System.out.println("Stereo off");
    }
    public void up() {
        System.out.println("Stereo up");
    }
}
