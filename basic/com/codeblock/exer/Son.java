package com.codeblock.exer;
/**
 * main，代码块，构造器执行顺序
 *  111111
    444444
    777777
    ******
    222222
    333333
    55555
    666666
    ******
    222222
    333333
    55555
    666666
    ******
    222222
    333333
 */
class Father {
    static {
        System.out.println("111111");
    }
    {
        System.out.println("222222");
    }

    public Father() {
        System.out.println("333333");
    }
}

public class Son extends Father {
    static {
        System.out.println("444444");

    }
    {
        System.out.println("555555");

    }

    public Son() {
        System.out.println("666666");
    }

    public static void main(String[] args) {
        System.out.println("777777");
        System.out.println("******");
        new Son();
        System.out.println("******");
        new Son();
        System.out.println("******");
        new Father();
    }
}
