package com.design.pattern.bridge;

public class StrightPhone extends Phone {

    public StrightPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("StrightPhone");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("StrightPhone");
    }
    @Override
    protected void call() {
        super.call();
        System.out.println("StrightPhone");
    }
}
