package com.design.pattern.factory.simplefactory;

/**
 * 披萨类(抽象类)
 */
public abstract class Pizza {
    protected String name; // 名字

    public void setName(String name) {
        this.name = name;
    }

    public abstract void prepare();

    public void bake() {
        System.out.println(name + "baking");
    }

    public void cut() {
        System.out.println(name + "cuting");
    }

    // 打包
    public void box() {
        System.out.println(name + "cuting");
    }

}
