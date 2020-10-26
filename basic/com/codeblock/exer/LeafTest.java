package com.codeblock.exer;
/**
 * 关于 包含父子类的代码块构造器的执行顺序
 * 由父及子 ，静态先行
 */
public class LeafTest {
    public static void main(String[] args) {
        new Leaf();
        System.out.println();
        new Leaf();
    }
    // static Root 静态初始化块
    // static Mid 静态初始化块
    // static Leaf 静态初始化块
    // 非静态 Root 普通初始化块
    // Root 无参数的构造器
    // 非静态 Mid 普通初始化块
    // Mid 无参数的构造器
    // Mid 有参数的构造器 参数是: 我是参数
    // 非静态 Leaf 普通初始化块
    // Leaf 构造器

    // 非静态 Root 普通初始化块
    // Root 无参数的构造器
    // 非静态 Mid 普通初始化块
    // Mid 无参数的构造器
    // Mid 有参数的构造器 参数是: 我是参数
    // 非静态 Leaf 普通初始化块
    // Leaf 构造器
}

class Root {
    static {
        System.out.println("static Root 静态初始化块");
    }

    {
        System.out.println("非静态 Root 普通初始化块");
    }

    public Root() {
        super();
        System.out.println("Root 无参数的构造器");
    }
}

class Mid extends Root {
    static {
        System.out.println("static Mid 静态初始化块");

    }
    {
        System.out.println("非静态 Mid 普通初始化块");
    }

    public Mid() {
        super();
        System.out.println("Mid 无参数的构造器");
    }

    public Mid(String msg) {
        // 通过this调用同一类重载的构造器
        this();
        System.out.println("Mid 有参数的构造器 参数是: " + msg);

    }
}

class Leaf extends Mid {
    static {
        System.out.println("static Leaf 静态初始化块");

    }

    {
        System.out.println("非静态 Leaf 普通初始化块");

    }

    public Leaf() {
        // 通过super调用父类带有字符串参数的构造器
        super("我是参数");
        System.out.println("Leaf 构造器");

    }

}