package com.design.pattern.template;

/**
 * 抽象类 模块方式
 * 表示豆浆
 */
public abstract class SoyaMilk {

    // 固定的模板 做成final是为了让子类继承的时候不被重写
    final void make() {
        select();
        addCondiments();
        soak();
        beat();
    }

    // 1 选材料
    void select() {
        System.out.println("1 SoyaMilk select");
    }

    // 2 添加材料 需要每个子类具体进行实现 所以是抽象方法
    abstract void addCondiments();

    // 3 开始浸泡
    void soak() {
        System.out.println("3 SoyaMilk soak");
    }

    // 4 打碎
    void beat() {
        System.out.println("4 SoyaMilk beat");
    }

}
