package com.abstractoop.exer;

/**
 * 抽象类的应用 
 * 模板方式的设计模式2
 */
public class TemplateTest2 {
    public static void main(String[] args) {

    }
}

abstract class BankTemplateMethod {

    public void takeNumber() {
        System.out.println("takeNumber");
    }

    public abstract void transact(); // 钩子 办理具体业务

    public void evaluate() {
        System.out.println("evaluate");
    }

    // 模板方法，把基本组合写一起，子类不能不能重写
    // 只能重写那些自己独有的方法，比如transact
    public final void processs() {
        this.takeNumber();
        this.transact(); // 像个钩子，类似于js的回调函数。具体执行时，挂哪个子类，就执行哪个子类的实现代码
        this.evaluate();
    }
}

class DrawMoney extends BankTemplateMethod {
    public void transact() {
        System.out.println("I want to DrawMoney");
    }
}

class ManageFinancial extends BankTemplateMethod {
    public void transact() {
        System.out.println("I want to ManageFinancial");
    }
}
