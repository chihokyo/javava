package com.polymorphism.exer;

// import java.sql.Connection;

/**
 * 多态性举例
 */
public class AnimalTest {
    
    public static void main(String[] args) {
        
        AnimalTest at = new AnimalTest();

        at.func(new Dog()); // Dog eat bones Dog wangwang 狗特有的方法
        at.func(new Cat()); // Cat eat Fish Cat miaomiao 狗特有的方法
    }

    public void func(Animal animal) {
        animal.eat();
        animal.wow();
    }

    // 如果没有了多态性，那么就只能继续写多2个方法
    // 就会省很多重载的方法了
    // public void func(Dog dog) {
    //     dog.eat();
    //     dog.wow();
    // }

    // public void func(Cat cat) {
    //     cat.eat();
    //     cat.wow();
    // }
}

class Animal{

    public void eat() {
        System.out.println("Animal eat");
    }

    public void wow() {
        System.out.println("Animal wow");
    }
}

class Dog extends Animal{

    public void eat() {
        System.out.println("Dog eat bones");
    }

    public void wow() {
        System.out.println("Dog wangwang");
    }
}

class Cat extends Animal{

    public void eat() {
        System.out.println("Cat eat Fish");
    }

    public void wow() {
        System.out.println("Cat miaomiao");
    }
}

/************举例2**************/

// 往大的说，一旦你有一个函数，接收的是一个对象的参数。
// 那么有了多态，就有了通用性，随便放进去一个对象都通用

// class OrderSample {

//     public void name(Object obj) {
        
//     }
// }

/************举例3**************/

// class Order {

//     public void doData(Connection con) { // con = new MySQLConnection();
//         // 规范的步骤操作数据 
//         // 无论什么数据库（Sql,mysql sqlite）进入到这里面都一样
//         // 这样就在父类定义了3个步骤,真正的连接都可以
//         // 这样子类就一定会重写。真正使用起来的时候，你只要把你的子类的方法进行重写就可以了
//         // 类似于这样
//         // con = new MySQLConnection();
//         // con = new OracleConnection();

//         con.method1();
//         con.method2();
//         con.method3();
//     }
// }