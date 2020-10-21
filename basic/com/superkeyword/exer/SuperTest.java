package com.superkeyword.exer;
/**
 * super 关键字
 *     1 super 理解为父类
 *     2 super 可以用来调用：属性，方法，构造器
 *     3 super 怎么用呢 
 *            3-1【关于属性】 子父类拥有相同的属性 this默认为当前对象,需要调用父类的 super关键字
 *            如果在自己的没找到，本身找不到就会想前找。这样就会层层的向上找 this往往都会省略掉
 *            name 和 this.name 区别就是 第一个会在自己找不到的情况下想前层层去找。但是this.name直接就用自己的了
 *            3-2 【关于方法】也是一样的，同理。当子类和父类方法相同的时候，需要显式的进行super调用父类的方法，否则就会默认
 *                  使用子类的方法。
 *            3-3【关于构造器】
 *                  1 可以在子类的构造器中进行显示的用 super 调用父类构造器
 *                  2 super（形参列表） 必须声明在子类构造器的首行
 *                  3 按照构造器的原理，所以this(),super() 只能2选1 不能同时出现
 *                  4 有时候写子类的构造器 默认 IDE就设置了一个super 父类的空参构造器
 *                  5 父类没有空参构造器的时候，子类调用有时候可能会出错
 *                  6 在多个构造器中，至少有一个类的构造器中使用了super(形参列表)调用了父类的构造器
 */
public class SuperTest {
    public static void main(String[] args) {

        System.out.println("*******属性Test*******");
        Student s = new Student();
        s.show();
        System.out.println("*******方法Test*******");
        s.study();

        System.out.println("*******构造器Test*******");
        // 构造器Test
        Student s1 = new Student("Amy", 20, "IT");
        s1.show();
    }
}
