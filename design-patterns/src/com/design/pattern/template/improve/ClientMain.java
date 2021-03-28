package com.design.pattern.template.improve;
/**
 * 模块方式
 * 这个实现都是带方式的
 */
public class ClientMain {
    public static void main(String[] args) {
        System.out.println("*****红豆豆浆*****");
        SoyaMilk redsSoyaMilk = new RedBeanSoyaMilk();
        redsSoyaMilk.make();

        System.out.println("*****花生豆浆*****");
        SoyaMilk  peanuSoyaMilk = new PeanutSoyaMilk();
        peanuSoyaMilk.make();

        System.out.println("*****纯豆浆*****");
        SoyaMilk blenSoyaMilk  = new BlendSoyaMilk();
        blenSoyaMilk.make();
    }
}
