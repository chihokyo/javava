package com.design.pattern.bridge;

/**
 * 桥接模式 
 * 这里的Phone就是一个桥梁，连接了具体的Brand和具体的手机样式
 * 抽象 手机类
 */
public abstract class Phone {

    // 通过聚合品牌
    private Brand brand;

    // 构造器初始化一个品牌
    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }

    // 这里就是实现了Brand这个接口的方法
    protected void open() {
        // 实际调用的是传入的具体的Phone对象
        this.brand.open();
    }

    protected void close() {
        // 不写也可以 反正这里就一个brand对象
        brand.close();
    }

    protected void call() {
        this.brand.call();
    }
}
