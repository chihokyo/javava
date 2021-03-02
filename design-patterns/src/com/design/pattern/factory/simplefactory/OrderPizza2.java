package com.design.pattern.factory.simplefactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;


public class OrderPizza2 {
    
    Pizza pizza = null;
    String orderType = "";
    // 构造器
    public OrderPizza2() {
        do {
            orderType = getType();
            pizza = SimpleFactory.createPizza2(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                System.out.println("error");
                break;
            }
        } while (true);
    }


    private String getType(){
        String str = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类：");
            str = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
