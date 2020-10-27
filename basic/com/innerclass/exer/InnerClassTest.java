package com.innerclass.exer;
/**
 * 关于内部类
 * 类里面可以定义类，就是内部类。
 * 1 为什么要用内部类。
 *      a 作为属性 功能不够
 *      b 本身不具有通过性，写在外面也没用 
 * 2 分类
 *      成员内部类 （static成员内部，非static成员内部）
 *      局部内部类 （方法内，代码块内，构造器内）
 * 3 成员内部类
 *      一方面，作为外部类的成员：
 * 			>调用外部类的结构
 * 			>可以被static修饰【因为正常的外部类，不能被static修饰啊】
 * 			>可以被4种不同的权限修饰
 * 
 * 		另一方面，作为一个类：
 * 			> 类内可以定义属性、方法、构造器等
 * 			> 可以被final修饰，表示此类不能被继承。言外之意，不使用final，就可以被继承
 * 			> 可以被abstract修饰
 * 4.关注如下的3个问题
 *   4.1 如何实例化成员内部类的对象
 *   4.2 如何在成员内部类中区分调用外部类的结构
 *          Person.this.eat(); // 要明示外部类的类名
 *   4.3 开发中局部内部类的使用  见《InnerClassTest1.java》
 */
public class InnerClassTest {
    public static void main(String[] args) {
        // 如何实例化成员内部类的对象(静态)
        Person.Heart pHeart = new Person.Heart();
        pHeart.beat();
        // 如何实例化成员内部类的对象(非静态)
        // Person.Leg pLeg = new Person.Leg(); NG
        Person p = new Person();
        Person.Leg pLeg = p.new Leg();
        pLeg.kick();

        pLeg.display("thin");

    }
}

class Person {
    String name = "Amy";
    int age = 29;

    public void eat() {
        System.out.println("Person eat");
    }

    /******************** 成员内部类 **********************/
    // static成员内部类
    static class Heart {
        String name;
        int age;

        public void beat() {
            System.out.println("Heart beat");
            // eat(); NG static无法调用非静态方法 因为生命周期加载顺序不一样
        }
    }

    // 非static成员内部类
    class Leg {
        String name = "Big Leg";

        public Leg() {

        }

        public void kick() {
            System.out.println("Leg Kick");
            Person.this.eat(); // 外部类的非静态属性
            eat();// 这是简写 注意是Person.this 只是单纯的this.name 说的就是leg了
            System.out.println(age);
        }

        public void display(String name) {
            System.out.println(name); // 方法的形参
            System.out.println(this.name);// 内部类的属性
            System.out.println(Person.this.name); // 外部类的属性
        }
    }

    /******************** 局部内部类 **********************/
    // 方法内
    public void method() {
        class AA {

        }
    }

    // 代码块内
    {
        class BB {

        }
    }

    // 构造器内
    public Person() {
        class CC {

        }
    }

}