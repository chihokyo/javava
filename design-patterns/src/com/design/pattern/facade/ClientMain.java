package com.design.pattern.facade;

/**
 * 这里开始外观模式
 */
public class ClientMain {
    public static void main(String[] args) {
        // 如果没有外观模式的话
        // 这里就需要直接new 各种小家电
        // 但是有了外观模式的话，那么直接就可以把逻辑写在一个中间层里   
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.end();
    }
}
