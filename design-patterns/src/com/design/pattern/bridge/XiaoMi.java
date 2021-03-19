package com.design.pattern.bridge;

public class XiaoMi implements Brand {

    @Override
    public void open() {
        System.out.println("XiaoMi Brand open");
    }

    @Override
    public void close() {
        System.out.println("XiaoMi Brand close");
    }

    @Override
    public void call() {
        System.out.println("XiaoMi Brand call");
    }
    
}
