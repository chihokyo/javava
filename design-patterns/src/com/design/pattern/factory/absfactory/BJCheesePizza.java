package com.design.pattern.factory.absfactory;

public class BJCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJCheesePizza");
        System.out.println("BJCheesePizza prepare...");
    }
}
