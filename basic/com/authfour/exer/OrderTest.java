package com.authfour.exer;

public class OrderTest {
    public static void main(String[] args) {
        Order order = new Order();

        order.orderDefault = 1;
        order.orderProtected = 2;
        order.orderPublic = 3;

        order.methodDefault();
        order.methodProtected();
        order.methodPublic();

        // // 同一个package 不同类 不能使用 私有属性和方法
        // order.orderPrivate;
        // order.methodPrivate();

    }
}
