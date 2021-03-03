package com.design.pattern.factory.factorymethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 抽象类 用来创建orderPizza
 */
public abstract class OrderPizza {

    // 抽象方法让子类实现创造具体的实例
    abstract Pizza createPizza(String orderType);

    // 构造器用来创建实例
    public OrderPizza() {
        Pizza pizza = null;
        String orderType;
        do {
            orderType = getType();
            pizza = createPizza(orderType);
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

    // 获取种类
    private String getType() {

        String str = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input type: ");
            str = br.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;

    }

}
