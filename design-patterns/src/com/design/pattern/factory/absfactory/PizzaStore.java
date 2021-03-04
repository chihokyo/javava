package com.design.pattern.factory.absfactory;

public class PizzaStore {
    public static void main(String[] args) {
        // new OrderPizza(new LDFactory());
        new OrderPizza(new BJFactory());
    }
}
