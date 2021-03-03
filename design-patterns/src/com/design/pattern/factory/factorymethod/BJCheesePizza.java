package com.design.pattern.factory.factorymethod;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJCheesePizza");
        System.out.println("BJCheesePizza prepare...");
    }
}
