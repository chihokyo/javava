package com.design.pattern.factory.absfactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class OrderPizza {

    // 一个属性 存放工厂
    AbsFactory absFactory;
    // 构造器初始化工厂
    public OrderPizza(AbsFactory absFactory) {
        setFac(absFactory);
    }
    // 具体初始化实现
    private void setFac(AbsFactory absFactory) {

        Pizza pizza = null;
        String orderType = "";
        // 这里需要先放进来
        this.absFactory = absFactory;
        do {
            // 根据具体类型放进去调用具体的工厂类
            orderType = getType();
            // 开始制造 其实这里用的是多态
            pizza = absFactory.createPizza(orderType);
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
