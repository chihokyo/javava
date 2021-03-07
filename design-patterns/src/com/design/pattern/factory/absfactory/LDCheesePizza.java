package com.design.pattern.factory.absfactory;

public class LDCheesePizza extends Pizza {
    @Override
    public void prepare() {
        setName("LDCheesePizza");
        System.out.println("LDCheesePizza prepare...");
    }
}