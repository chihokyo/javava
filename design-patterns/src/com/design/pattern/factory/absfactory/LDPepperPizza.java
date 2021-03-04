package com.design.pattern.factory.absfactory;

public class LDPepperPizza extends Pizza {
    @Override
    public void prepare() {
        setName("LDPepperPizza");
        System.out.println("LDPepperPizza prepare...");
    }
}