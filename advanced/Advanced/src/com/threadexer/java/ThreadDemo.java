package com.threadexer.java;
/**
 * 线程练习
 * 1个输出奇数
 * 1个输出偶数
 */
public class ThreadDemo {
    public static void main(String[] args) {
        /************ 方式1 ************/
        MyThreadA m1 = new MyThreadA();
        MyThreadA m2 = new MyThreadA();
        // 因为线程是CPU在来回切换，所以会有顺序每次都不一样
        m1.start();
        m2.start();

        /************ 方式2 ************/
        // 如果只是用1次并且不再复用。可以使用匿名子类的方式 new Thread(){}这里start的匿名子类，而不是Thread
        new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            };
        }.start();

        new Thread() {
            public void run() {
                for (int i = 0; i < 50; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            };
        }.start();
    }
}

// 线程1
class MyThreadA extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

// 线程2
class MyThreadB extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}
