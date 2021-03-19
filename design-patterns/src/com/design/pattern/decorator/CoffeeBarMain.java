package com.design.pattern.decorator;

public class CoffeeBarMain {
    public static void main(String[] args) {

        System.out.println("***假设点餐开始***");
        System.out.println("***黑咖啡1杯+巧克力2+豆奶1***");

        Drink order = new LongBlack();
        for (int i = 0; i < 2; i++) {
            order = new Chocolate(order);
        }
        order = new Soy(order);

        System.out.println(order.getDesc());
        System.out.println("费用:" + order.cost());
        
        System.out.println("***特浓咖啡1杯+巧克力1+豆奶1***");

        Drink order2 = new Espresso();
        order2 = new Soy(order2);
        order2 = new Chocolate(order2);
        System.out.println(order2.getDesc());
        System.out.println(order2.cost());

    }
}
