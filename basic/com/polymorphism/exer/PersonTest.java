package com.polymorphism.exer;
/**
 * 一。关于多态性 Polymorphism
 *      理解 1个事物的多种形态
 * 二。多态的使用
 *       当调用子父类同名同参数方法调用的时候，实际执行的是子类重写父类的方法 ---虚拟方法的调用
 *       此时父类的方法叫虚拟方法，注意，多态是一个动态绑定，是运行时行为。而不是编译时行为。
 * 三。多态的使用 虚拟方法的调用
 *      有了对象的多态性以后 
 *          编译 只能调用父类声明方法
 *          运行 实际执行子类重写父类的方法
 *      【编译看左，运行看右】
 * 四。使用前提
 *      1 有类的继承关系，有继承关系 才有多态
 *      2 要有子类重写父类（不然谁还new子类呢）
 * 五。为什么要使用多态呢
 *      AnimalTest 进行说明了
 * 六。属性的多态是没有的。
 *      【对象多态性只适用于方法，不适用于属性。不存在多态性。】
 *          heap里面虽然都有2个相同属性，但是都是要看左边的。
 * 
 */
public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.eat();

        Man m1 = new Man();
        m1.eat();
        m1.age = 25;
        m1.fight();

        Woman w1 = new Woman();
        w1.eat();

        /************* 以上都是正常调用 *************/

        // 父类的引用指向子类的对象
        // p2 就是父类引用 new Man() or new Woman() 就是子类的对象
        Person p2 = new Man(); // 并没有new Man 而不是多态性
        // Person p3 = new Woman();
        // 对象的多态性，虽然是一个对象，但是对象有多种形态。只要是Man的子类都可以进行new起来

        System.out.println("*************华丽的分割线【方法】*************");
        // 多态的使用，当调用子父类同名同参数方法调用的时候，实际执行的是子类重写父类的方法 ---虚拟方法的调用
        p2.eat(); // Q:父类 or 子类？ A: Man eat more 就是子类的
        p2.walk(); // Man walk slow

        // p2.fight(); NG 但是却无法调用子类独有的方法
        // 编译 编译用的是父类方法
        // 运行 子类重写的方法

        System.out.println("*************华丽的分割线【属性】*************");
        System.out.println("p2.id = " + p2.id); // p2.id = 100 父类的id

    }
}
