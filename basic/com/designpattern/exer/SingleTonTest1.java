package com.designpattern.exer;
/**
 * 单例设计模式1
 * 1 什么是单例设计模式？1个类只能存在1个实例
 * 2 如何实现？
 * 饿 一上来就已经在类里存在，使用的时候直接调用就可以了
 * 懒 什么时候用，什么时候new
 * 3 区别是什么？
 * 饿汉（先new）  坏处：对象加载时间过程 static 只要一份，但是弊端就是生命周期过长
 *              好处：线程安全（并发时候数据的原子性）
 * 懒汉（后new） 延迟对象的创建
 *              坏处：非线程安全 ====>>> 多线程内容的时候会进行修改
 * 
 * 4 单例设计模式的优点
 * 由于单例模式只会生成1个实例，减少了系统内存的开箱，所以当一个对象产生比较多的资源的时候，
 * 如读取配置，产生其他依赖对象的时候，可以在程序启动的时候产生一个单例对象，然后永驻在内存方式来解决
 * 在系统的类里面【java.lang.Runtime就是这样的方式】
 * 5  单例模式的应用场景
 * 1 网站的计数器 （PV，UV）便于同步
 * 2 应用程序的日志应用。一般只能由一个实例去操作，否则内容不好追加
 * 3 数据库连接池。
 * 4 项目中读取配置的类
 * 5 Application
 * 6 Windows Task Manager（任务管理器）
 * 7 Windows Recycle Bin（回收站）
 */
public class SingleTonTest1 {
    public static void main(String[] args) {
        Bank b1 = Bank.getInstance();
        Bank b2 = Bank.getInstance();
        System.out.println(b1 == b2); // true
    }
}

// 希望这个bank类只有1个对象 饿汉式
class Bank {

    // 1 私有化里的构造器 → 保证只有这个类内部才能进行new
    // 比如在上面的 SingleTonTest main函数里面就不能通过 new Bank进行新建对象
    private Bank() {

    }

    // 2 内部创建类的对象 → 4 要求这个属性也是static
    // 这个对象也可以理解为 Bank类的属性
    // instance 只是这个属性值恰好就是这个类的对象
    private static Bank instance = new Bank();

    // 3 提供公共的【静态】方法，返回类的对象
    // 为什么这里要用static ，
    // 因为如果要实例化调用这个方法，会发现由于是私有的，是无法通过new来创建的
    // 这样下去会有一个死循环，如何打破这个死循环，就是需要在类里直接就有这个方法和属性
    // 那么就是 static
    public static Bank getInstance() {
        return instance;
    }
}
