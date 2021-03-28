package com.design.pattern.template;

/**
 * 一个案例-模块方式
 * 表示一个豆浆的制作过程
 * 根据添加不同的配料，制作出不同的口味
 * 口味是变化的
 * 但是固定的浸泡打碎的方法是不变的
 */
public class ClientMain {
    public static void main(String[] args) {
        
        System.out.println("*****红豆豆浆*****");
        SoyaMilk redsSoyaMilk = new RedBeanSoyaMilk();
        redsSoyaMilk.make();

        System.out.println("*****花生豆浆*****");
        SoyaMilk  peanuSoyaMilk = new PeanutSoyaMilk();
        peanuSoyaMilk.make();
    }
}
