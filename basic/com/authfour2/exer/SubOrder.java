package com.authfour2.exer;


import com.authfour.exer.Order;

public class SubOrder extends Order {
    
    public void method() {
        orderProtected = 1;
        orderPublic = 2;

        methodProtected();
        methodPublic();
    
        // 不同包子类继承父类，缺省和private不行

        // orderDefault = 3;
        // orderPrivate = 4;

        // methodPrivate();
        // methodDefault();
    } 

}
