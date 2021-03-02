package com.design.pattern.factory.simplefactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 订购披萨
 */
public class OrderPizza {
    
    SimpleFactory simpleFactory;
    Pizza pizza = null;
    
    // 构造器 订购披萨的时候要传入工厂对象
    public OrderPizza( SimpleFactory simpleFactory) {
        setFactory(simpleFactory);
    }

    public void setFactory(SimpleFactory simpleFactory) {
        // 变量存储用户输入数据
        String orderType = "";
        this.simpleFactory = simpleFactory; // 设置简单工厂模式
        do {
            // 获取披萨种类
            orderType = getType();
            // 创造一个pizza实例
            pizza = this.simpleFactory.createPizza(orderType);
            // 进行准备工作
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

    // 获取披萨种类
    public String getType() {
        String str = "";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input 种类：");
            str = br.readLine();
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
