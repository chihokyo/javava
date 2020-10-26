package com.codeblock.exer;
/**
 * 类成员4 代码块（初始化代码）
 * 1 代码块的作用 用来初始化类or对象
 * 2 代码块如果有修饰，只能是static
 * 3 静态代码块 非静态代码块
 * 
 * 4. 静态代码块
 * 	   >内部可以有输出语句
 * 	   >随着类的加载而执行,而且只执行一次
 * 	   >作用：初始化类的信息
 * 	   >如果一个类中定义了多个静态代码块，则按照声明的先后顺序执行
 * 	   >静态代码块的执行要优先于非静态代码块的执行
 * 	   >静态代码块内只能调用静态的属性、静态的方法，不能调用非静态的结构
 * 
 * 5. 非静态代码块
 * 		>内部可以有输出语句
 * 		>随着对象的创建而执行
 * 		>每创建一个对象，就执行一次非静态代码块
 * 		>作用：可以在创建对象时，对对象的属性等进行初始化
 * 		>如果一个类中定义了多个非静态代码块，则按照声明的先后顺序执行
 * 		>非静态代码块内可以调用静态的属性、静态的方法，或非静态的属性、非静态的方法
 * 
 * 执行顺序优先级：静态块,main(),构造（非静态）块,构造方法。
 * 
 * 对属性可以赋值的位置
 *  默认初始化 int age
 *  显式初始化 int age = 19;
 *  构造器初始化 public Person
 *  有了对象之后，可以【对象.属性】【对象.方法】进行赋值 p1.age
 *  在代码块中赋值
 * 
 * 关于使用场景 ，在 数据库连接池的时候可以进行运用
 */
public class BlockTest {
    public static void main(String[] args) {
        String desc = Person.desc;
        System.out.println(desc);

        Person p1 = new Person();
        Person p2 = new Person();
        System.out.println(p1.age);

        Person.info();

        // static 代码块
        // static 代码块2
        // info
        // static 代码块-调用静态结构
        // 非static 代码块
        // 非static 代码块2
        // eat
        // info
        // 非static 代码块
        // 非static 代码块2
        // eat
        // info
        // 1
        // info
    }
}


class Person {
    String name;
    int age;
    static String desc = "static desc";

    public Person() {
        
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 非static 代码块
    {
        System.out.println("非static 代码块");
    }
    {
        System.out.println("非static 代码块2");
        // 调用非静态结构
        age = 1;
        eat();
        // 调用静态结构
        desc = "非static 代码块-调用静态结构";
        info();

    }

    // static 代码块
    static {
        System.out.println("static 代码块");
    }
    static {
        System.out.println("static 代码块2");
        
        // 调用静态结构
        desc = "static 代码块-调用静态结构";
        info();
        // NG 非静态结构 根本不能用
        // age = 1;
        // eat();
    }

    public void eat() {
        System.out.println("eat");
    }

    @Override
    public String toString() {
        return "Person" + name + age;
    }

    public static void info() {
        System.out.println("info");
    }

}