package com.design.pattern.facade;

public class DVDPlayer {
    // 这里使用的是单例模式
    private static DVDPlayer instance = new DVDPlayer();
    public static DVDPlayer getInstance() {
        return instance;
    }
    public void on() {
        System.out.println("DVDPlayer on");
    }
    public void off() {
        System.out.println("DVDPlayer off");
    } 
    public void play() {
        System.out.println("DVDPlayer play");
    } 
    public void pause() {
        System.out.println("DVDPlayer pause");
    } 
}
