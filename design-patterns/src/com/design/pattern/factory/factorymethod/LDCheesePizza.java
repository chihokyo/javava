package com.design.pattern.factory.factorymethod;

public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("LDCheesePizza");
        System.out.println("LDCheesePizza prepare...");
    }
}