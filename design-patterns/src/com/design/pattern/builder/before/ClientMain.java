package com.design.pattern.builder.before;

/**
 * 开始建造
 */
public class ClientMain {
    public static void main(String[] args) {
        System.out.println("建造一个普通房子");
        CommonHouse ch = new CommonHouse();
        ch.build();
        System.out.println("建造一个高房子");
        HighHouse hh = new HighHouse();
        hh.build();
    }
}
