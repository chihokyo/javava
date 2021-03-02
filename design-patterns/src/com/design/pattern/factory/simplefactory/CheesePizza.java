package com.design.pattern.factory.simplefactory;
/**
 * 起司类(继承披萨)
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("CheesePizza prepare...");
    }
}
