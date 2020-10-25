package com.statickeyword.exer;

/**
 * static关键字的使用
 *      1 static 静态的
 *      2 static 可以用来修饰：属性，方法，代码块，内部类【构造器不行】
 *      3 【修饰属性】-静态变量（当然也无法修饰局部变量）又叫 类变量
 *              按照是否有static 可以分成 静态属性 PK 非静态属性（实例变量）=====>>>>实例自己独立一份的
 *              3-1 实例变量(属性) 没有使用static修饰的属性 创建了多个对象，每个对象
 *              3-2 静态变量。每个对象都共享同一个静态变量。当通过某一个对象修改静态变量时，会导致其他变量调用的时候是修改过的。
 *      4 static 
 *          4-1 静态类随着类加载而加载  类.静态变量
 *          4-2 静态变量早于对象创建 
 *          4-3 因为类只会加载一次，所以类变量也只会存在一份 存在方法区域的静态域当中
 *          4-4     类变量 实例变量
 *              类   yes    no（因为还没对象呢，怎么调）
 *              对象 yes   yes
 *      5 【修饰方法】
 *          static 修饰的方法就是静态方法
 *          5-1 随着类的加载而加载 类.静态方法 
 *          5-2 使用起来注意点 几乎和类变量一样，在类方法里不能使用this(因为当前对象还没有呢)，和实例方法
 *          5-3 类加载 对象的创建 （非静态结构加载） 对象的消亡 类的消亡  静态消亡（JVM内存加载）
 *          5-4 要考虑上面的生命周期来考虑是否能够使用 静态方法 静态变量
 *      6 关于什么时候使用静态属性？ 基本上公用的，不会随着对象的不同而不同的东西。
 *        关于什么时候使用方法呢？
 *          操作静态属性的方法，通常是static
 *          工具类中的方法，习惯声明为static 比如 Arrays, Math, Collections
 */

public class StaticTest {
    public static void main(String[] args) {
        // 因为静态属性随着类加载而加载，所以可以通过类直接调用
        Chinese.nation = "Big CHINA";

        Chinese c1 = new Chinese();
        c1.name = "chin";
        c1.age = 39;

        Chinese c2 = new Chinese();
        c2.name = "chin2";
        c2.age = 50;

        c1.nation = "CHN";
        // 虽然没有赋值，但是就是可以输出
        System.out.println(c2.nation); // CHN
        c2.nation = "china";
        System.out.println(c1.nation); // china
        System.out.println(c2.nation); // china

        c1.eat(); // Chinese eat
        Chinese.show(); // Chinese static show
        // Chinese.eat(); NG
    }
}

class Chinese {
    String name;
    int age;
    static String nation;

    public void eat() {
        System.out.println("Chinese eat");
    }

    public static void show() {
        // 不能调用非静态的结构
        // eat();NG
        // this; super;NG
        // System.out.println(name); NG
        // 可以调用的结构
        System.out.println("Chinese static show");
        System.out.println(nation); // china 【此时省略的是 Chinese.nation】
        walk();
    }

    public static void walk() {
        System.out.println("Chinese static walk"); // Chinese static walk
    }
}