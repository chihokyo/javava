package com.polymorphism.exer;

/**
 * 一个关于重写重载多态的练习题
 */
public class PolyExer4 {
    public static void main(String[] args) {
        // 多态性体现 父类地址 指向 子类实例对象
        PolyExerBase base = new SubPolyExerBase();
        // 按照编译在做，运行在右的原则，实际上执行的是子类的add
        // 这里第1个坑就是子类的 add实际上是重写了 父类的 add
        // int a, int... arr ===== int a, int[] arr
        // 所以出现的是 Sub1
        base.add(1, 2, 3); // Sub1

        // 这里运用了强制转换，使父类也有了子类独有的方法
        // 第2个坑就是 add这里使用了重载，按照参数列表实际上使用的是 add(int a, int b, int c)
        SubPolyExerBase s = (SubPolyExerBase) base;
        s.add(1, 2, 3); // Sub2
    }
}

class PolyExerBase {
    public void add(int a, int... arr) {
        System.out.println("PolyExerBase");
    }
}

class SubPolyExerBase extends PolyExerBase {

    public void add(int a, int... arr) {
        System.out.println("Sub1");
    }

    public void add(int a, int b, int c) {
        System.out.println("Sub2");
    }
}
