package com.threadexer.java;

/**
 * 关于死锁的演示2 
 * 这个时候主线程和副线程是2个不一样的
 */
public class DeadLockSample2 implements Runnable {

    A a = new A();
    B b = new B();

    public static void main(String[] args) {
        DeadLockSample2 dl = new DeadLockSample2();
        new Thread(dl).start(); // 开启分线程 ①
        dl.init(); // ②
        // 主线程调用的 init 方法 这个时候有2个线程了
        // 也就是说在这个地方有两个线程同时开启，同时在运作。

        // 主线程路线：这个时候a.foo(b)方法- 需要锁A
        // 需要b.last() 是一个线程 需要锁B

        // 分线程路线：先run() 然后需要 b.bar(a); 进入到 需要锁B
        // 然后 a.last(); 需要锁A
    }

    public void init() {
        Thread.currentThread().setName("主线程");
        a.foo(b);// ③
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        b.bar(a);
        System.out.println("进入了副线程之后");
    }
}

class A {

    public synchronized void foo(B b) { // 这里的锁是A的实例对象 ④
        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 进入了A实例的foo方法");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 准备调用B实例的last()方法");
        b.last(); // ⑤
    }

    public synchronized void last() {
        System.out.println("进入了A类的last方法内部");
    }
}

class B {
    public synchronized void bar(A a) {
        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 进入了B实例的foo方法");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("当前线程名: " + Thread.currentThread().getName() + " 准备调用A实例的last()方法");
        a.last();
    }

    public synchronized void last() { // 锁b
        System.out.println("进入了B类的last方法内部"); // ⑥
    }
}