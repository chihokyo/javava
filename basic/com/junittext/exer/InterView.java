package com.junittext.exer;

public class InterView {
    public static void main(String[] args) {
        Object o1 = true ? Integer.valueOf(1) : new Double(2.0);
        System.out.println(o1); // 类型提升 1.0 要统一类型

        Object o2;
        if (true) {
            o2 = Integer.valueOf(1);
        } else {
            o2 = Double.valueOf(2.0);
        }

        System.out.println(o2); // 1 没有类型提升

        System.out.println("************Integer缓存*****************");

        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j); // false 比较地址 肯定不是同一个对象

        // Integer中有一个 IntegerCache定义了Integer[]数组，范围是 -128 ~ 127
        // -128 ~ 127不用new 也不会被销毁，提高装箱效率
        Integer m = 1;
        Integer n = 1;
        System.out.println(m == n); // true

        Integer x = Integer.valueOf(128);
        Integer y = Integer.valueOf(128);
        System.out.println(x == y); // false

    }
}
