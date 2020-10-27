package com.interfacetest.exer;
/**
 * 一段测试
 */

interface A {
    int x = 0;
    int y1 = 10;
}

class B {
    int x = 1;
    int y2 = 20;
}

class C extends B implements A {
    public void pX() {
        // 【变量名相同 都是x】
        // System.out.println(x); // 编译NG The field x is ambiguous
        // System.out.println(this.x); // 编译NG The field x is ambiguous 因为上面其实就是省略了this 都一样
        System.out.println(super.x); // 1 最近的父类 而分接口
        System.out.println(A.x); // 0 接口的变量就是全局的，直接输出就行

         // 【变量名不同 y1,y2】
         System.out.println(y1); // 10 不会出问题 正常输出
         System.out.println(y2); // 20 不会出问题 正常输出
    }
    public static void main(String[] args) {
        new C().pX();
    }
}

/*************练习2**************/


interface Playable {
    void play();
}

interface Bounceable {
    void play();
}
interface Rollable extends Playable, Bounceable {
    Ball ball = new Ball("Ping Pang");
}

class Ball implements Rollable {

    private String name;
    public String getName(){
        return name;
    }
    public Ball(String name) {
        this.name = name
    }

    @Override
    public void play() {
        // 接口内的属性都是 public static final 不能重写 不能继承
        // ball = new Ball("jdehiewh"); // The final field Rollable.ball cannot be assigned
        System.out.println(ball.getName());
    }
}


