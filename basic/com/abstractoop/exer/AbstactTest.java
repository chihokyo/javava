package com.abstractoop.exer;
/**
 * 关于abstract
 * 感觉对于抽象方法的诞生应该是要理解的。
 * 那就是随着项目的变大，发现很多子类继承了父类，而且一层一层的
 * 这时候就会发现最上面的一层的父类根本就没有被实例化，几乎都是底下的子类才进行实例化
 * 这样父类就会被架空了，那么既然被架空了，那么就变得越来越抽象了。
 * 
 * 
 * 包含抽象方法的类，一定是抽象类，不让对象去调用，那么抽象类抽象了就可以不用调用了
 * 抽象类可以没有抽象方法
 * 
 * 1.abstract:抽象的
 * 2.abstract可以用来修饰的结构：类、方法
 * 
 * 3. abstract修饰类：抽象类
 * 		> 此类不能实例化
 *      > 抽象类中一定有构造器，便于子类实例化时调用（涉及：子类对象实例化的全过程）
 *      > 开发中，都会提供抽象类的子类，让子类对象实例化，完成相关的操作
 * 
 * 
 * 4. abstract修饰方法：抽象方法
 * 		> 抽象方法只有方法的声明，没有方法体
 * 		> 包含抽象方法的类，一定是一个抽象类。反之，抽象类中可以没有抽象方法的。
 *      > 若子类重写了父类中的所有的抽象方法后，此子类方可实例化
 *        若子类没有重写父类中的【所有】的抽象方法，则此子类也是一个抽象类，需要使用abstract修饰
 */
public class AbstactTest {
    public static void main(String[] args) {
        // 一旦抽象了，就无法进行实例化 Cant new
        // Person p1 = new Person();
        // p1.eat();
    }
}


abstract class Creature {
    abstract public void breathe();
}

// 为什么有抽象方法的类就一定要是抽象的
// 因为为了不要对象去调用，那么抽象类抽象了就可以不用去调用了
abstract class Person extends Creature {
    String name;
    int age;
    // 为什么都不需要new了，还需要写构造器
    // 因为虽然自己不写了，但是子类还是要super到的
    public Person() {

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 不是抽象方法，不包含方法体
    // public void eat() {
    // }

    // 是抽象方法
    public abstract void eat();

    // 抽象方法 =====一定要是==== 抽象类
    // 抽象类 ====不一定要有==== 抽象方法
    public void walk() {
        System.out.println("Person Walk");
    }
}

class Student extends Person {
    public Student(String name, int age) {
        super(name, age);
    }
    public Student() {
    }
    // 学生可以不是抽象类，也可以没有抽象方法。
    // 但是里面的方法要保证自己继承的所有相关联父类的所有方法都要【重写override】
    public void eat(){
        System.out.println("Student eat");
    }

    @Override
    public void breathe() {
        System.out.println("Student breathe");
    }

}
