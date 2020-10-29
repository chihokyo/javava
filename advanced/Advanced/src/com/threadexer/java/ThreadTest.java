package com.threadexer.java;
/**
 * 多线程的创建 1 继承与 Thread 类 
 *      ①创建继承Thread类的子类 → 
 *      ②重写run()方法 → 线程执行的操作方法体写在run里面
 *      ③创建子类对象 → 
 *      ④通过此对象start() 例子
 * 遍历100以内所有的偶数
 * start()方法2个作用。1线程开始执行t1执行 2java虚拟机去调用run方法
 * 我们不能通过run() 直接去调用线程
 */ 
public class ThreadTest {
    public static void main(String[] args) {
        MyThread t1 = new MyThread(); // 这个线程目前还是主线程做的
        t1.start();// ①启动当前线程 ②调用当前线程run()
        // t1对象这个线程是在主线程里面造的 只有run 才是子线程

        System.out.println("hello"); // 这也是主线程做的

        /******** 上面的t1子线程和System输出其实是2个一起同时执行的线程 ************/
        /******** 所以输出的hello不一定会在哪里 ************/

        // 这里的操作还依然是在main里面执行的
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " main()");
            }
        }

        // 能否不调用 start() 直接调用 t1.run()
        // 不会报错，但是这是非线程。根本就没线程
        // 就相当于新建对象，然后调用run()方法
        // 如何验证呢？
        // 就使用
        // Thread.currentThread().getName()

        // 在启动一个线程
        // t1.start(); NG java.lang.IllegalThreadStateException 会有异常 不可以同时
        MyThread t2 = new MyThread();
        t2.start();

    }
}

// 子线程类
class MyThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}