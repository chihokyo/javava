package com.polymorphism.exer;

/**
 * 一个多态的练习
 * 
 * 1 若子类重写了父类方法，就意味着子类方法彻底覆盖了父类里的同名方法。【记住，是方法】
 * 2 系统不可能将父类的方法转移到子类里面
 * 3 对于实例变量则不存在这样的现象，属性是不可能被覆盖的。谁声明就是调用谁。编译运行都看左。
 * 
 */
public class PolyExer {
    public static void main(String[] args) {

        Sub s = new Sub();
        System.out.println(s.count); // 20 肯定是20 同名属性谁new用谁
        s.display(); // 20
        System.out.println(s); // com.polymorphism.exer.Sub@2f0e140b

        Base b = s; // 相当于把s的地址给了b 多态性
        // 引用数据类型 == 比较的是地址值
        System.out.println(b == s); // true
        System.out.println(b.count); // 10 ∵属性 ∴编译运行都看左边 
        b.display(); // 20 b现在指向的是s的heap地址 虚拟方法调用

    }

}

class Base {

    int count = 10;

    public void display() {
        System.out.println(this.count);
    }

}

class Sub extends Base {

    int count = 20;

    public void display() {
        System.out.println(this.count);
    }

}