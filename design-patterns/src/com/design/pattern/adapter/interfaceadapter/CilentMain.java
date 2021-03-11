package com.design.pattern.adapter.interfaceadapter;

/**
 * 想用哪个实现哪个
 * 抽象类也可以new 本质是子类继承了然后只重写了部分方法
 */
public class CilentMain {
    public static void main(String[] args) {
        // 相当于弄了一个子类
        AbsInterfaceMethodImpl  aImpl = new AbsInterfaceMethodImpl(){
            @Override
            public void methodA() {
                System.out.println("我自己重写的哦");
            }
        };
        aImpl.methodA();
    }
}
