package com.design.pattern.factory.simplefactory;

public class PizzaStore {
    
    public static void main(String[] args) {
        // // 使用方法1
        // new OrderPizza(new SimpleFactory());
        // System.out.println("退出程序");

        // 使用方法2
        new OrderPizza2();
    }
}
