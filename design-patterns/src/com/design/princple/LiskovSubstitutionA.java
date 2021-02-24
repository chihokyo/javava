package com.design.princple;

public class LiskovSubstitutionA {
    public static void main(String[] args) {

        LiskovA a = new LiskovA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        LiskovB b = new LiskovB();
        // 这里的b是重写了func1 但是很容易就会忘记你已经重写了
        System.out.println("11 - 3 = " + b.func1(11, 3));
        System.out.println("1 - 8 = " + b.func1(1, 8));
        System.out.println("11 + 3 + 9 = " + b.func2(11, 3));

    }
}

/**
 * A类
 */
class LiskovA {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * B类 增加了一个新功能 在ab相加的基础上+9
 */
class LiskovB extends LiskovA {
    // 这里可以无意识重写了
    // 这时候就不满足里氏替换原则
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}