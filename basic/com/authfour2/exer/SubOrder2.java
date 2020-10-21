package com.authfour2.exer;

import com.authfour.exer.Order;

public class SubOrder2 {

    public static void main(String[] args) {
        
        Order order = new Order();
        order.orderPublic = 1;
        order.methodPublic();


        // 不同包，不同类，import过来只有public
        // order.orderDefault = 1;
        // order.orderProtected = 2;
        // order.orderPrivate = 3;

        // order.methodDefault();
        // order.methodProtected();
        // order.methodPrivate();

    }

    // 以下同理
    public void show(Order order) {
        order.orderPublic = 1;
        order.methodPublic();


        // 不同包，不同类，import过来只有public
        // order.orderDefault = 1;
        // order.orderProtected = 2;
        // order.orderPrivate = 3;

        // order.methodDefault();
        // order.methodProtected();
        // order.methodPrivate();
    }
}
