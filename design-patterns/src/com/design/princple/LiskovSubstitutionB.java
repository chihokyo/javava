package com.design.princple;

public class LiskovSubstitutionB {
    public static void main(String[] args) {
        LiskovAA a = new LiskovAA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("-------------------");
        LiskovBB b = new LiskovBB();
        // 因为BB不在继承AA类 所以调用不会再认为func1是减法了
        // 调用的功能就会明确
        System.out.println("11 + 3 = " + b.func1(11, 3));
        System.out.println("1 + 8 = " + b.func1(1, 8));

        // 使用组合依然可以使用到AA的相关方法
        // 这里的func3 其实调用的是AA类的
        System.out.println("11 - 3" + b.func3(11, 3));

    }
}

class LiskovBase {
}

/**
 * AA类
 */
class LiskovAA extends LiskovBase {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * BB类
 */
class LiskovBB extends LiskovBase {
    // 如果这个时候BB要使用AA的func1
    private LiskovAA a = new LiskovAA();

    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}