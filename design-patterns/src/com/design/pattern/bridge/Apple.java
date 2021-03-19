package com.design.pattern.bridge;

/**
 * 具体的品牌实现
 */
public class Apple implements Brand {

    @Override
    public void open() {
        System.out.println("Apple Brand open");
    }

    @Override
    public void close() {
        System.out.println("Apple Brand close");
    }

    @Override
    public void call() {
        System.out.println("Apple Brand call");
    }

}
