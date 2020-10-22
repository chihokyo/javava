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
 * *******************************************************************************************
 * 
 * Person p2 = new Man()； 因为是编译看左，运行看右，实际上只能调用重写过后的方法，也就是俩都有的
 * 如果这时候想调用子类特有的方法怎么办呢
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

        System.out.println("*************华丽的分割线【方法（子重写父的）】*************");
        // 多态的使用，当调用子父类同名同参数方法调用的时候，实际执行的是子类重写父类的方法 ---虚拟方法的调用
        p2.eat(); // Q:父类 or 子类？ A: Man eat more 就是子类的
        p2.walk(); // Man walk slow

        // p2.fight(); NG 但是却无法调用子类独有的方法
        // 编译 编译用的是父类方法
        // 运行 子类重写的方法

        System.out.println("*************华丽的分割线【属性】*************");
        System.out.println("p2.id = " + p2.id); // p2.id = 100 父类的id

        System.out.println("*************华丽的分割线【调用子类特有方法】*************");
        p2.name = "Amy"; // ok
        // p2.isSmoking; // NG 未定义，因为编译的时候认为p2是Person类型
        // 从内存结构来看，虽然说heap里面也加载了子类特有的属性和方法。但是因为变量声明的类型是Person（父类）类型
        // 导致编译时就只能调用父类的属性和方法

        // 那么如何调用子类特有的方法和属性呢 ？
        // Man m1 = p2;// NG 绝对不行，能赋值的情况，要么左边和右边数据类型一样，要么就是数据类型有自动类型提升

        // 所以最后使用强制类型转换符
        // 【属性】 较高级的数据类型(double) →→→→→强制类型转换→→→→→ 较低级的数据类型(int)
        // 【属性】 较低级的数据类型 →→→→→自动类型提升→→→→→ 较高级的数据类型
        // 【方法】 父类 →→→→→→→→→→→→→向下转型 instanceof判断→→→→→→→→→→→→→子类
        // 【方法】 子类 →→→→→→→→→→→→向上转型 多态→→→→→→→→→→→→→→父类

        Man m2 = (Man)p2; // 地址值 类型@地址 这个时候就是强制把@前面的类型转换
        m2.isSmoking  = true;
        m2.fight();

        // 但是所有强转都会有风险，属性的时候就有精度损失。盘

        // Man m2 = (Woman)p2; NG 这样就不行，因为内存本身就没有，可能会出现异常ClassCastException

        System.out.println("*************华丽的分割线【instanceof】*************");
        
        // 为了避免向下转型时候出现异常，都会在转移之前先进行instanceof判断
        // 返回true 才会转型
        // a instanceof A 是对的 
        // a instanceof A的所有父类 也是对的 


        if (p2 instanceof Woman) {
            Woman w2 = (Woman)p2;
            w2.beBeauty();
            System.out.println("p2 instanceof Woman");
        }

        if (p2 instanceof Man) { // p2 instanceof Person //  p2 instanceof Object
            Man m3 = (Man)p2;
            m3.fight();
            System.out.println("p2 instanceof Man");

        } // p2 instanceof Man 


        System.out.println("*************华丽的分割线【向下转型练习】*************");
        // 1 编译通过 运行不通过
        Person p4 = new Woman();
        Man m4 = (Man)p4;
        m4.fight();

        // 这样肯定不行，没意义。new了一个父类对象，本身就没有子类的方法。如何强转
        // Person p5 = new Person();
        // Man m5 = (Man)p5;


        // 2 编译通过 运行也通过
        Object obj = new Woman(); // 向上转型到了 object （Woman → Person → Object）
        Person p = (Person)obj; // object 又向下到了 Person
        p.eat();

        // 3 编译运行都不会通过
        // Man m6 = new Woman(); // 没有任何关系，也不是子类，如何new啊，也不是多态

        // 不相关的2个类型无论如何都不会有结果的。

    }
}
