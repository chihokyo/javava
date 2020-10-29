package com.threadexer.java;
/**
 * 创建线程的第二种方式 Runnable 实现接口
 * 
 * 1 创建一个实现了 Runnable 接口的类
 * 2 实现类去实现 Runnable 中抽象的方法 run()
 * 3 创建实现类的对象
 * 4 将上面的对象作为参数传递到Thread类的构造器里，创建 Thread 类的对象
 * 5 通过 Thread 类的对象调用 run
 * 
 * 对比 Thread 类的创建方法 来看主要的创建方式不同的地方就是
 * a 一个是继承 实例化对象
 * b 一个是实现接口后，实现类的对象
 * 
 * 比较2种线程不同的方式
 * 1 Runnable 没有单继承方式的局限性，通过实现而非继承。不会破坏子类本身继承的父类。线程的继承并不是一个
 * 很好的继承方式。可能类本身也有自己的父类，所以开发中优先使用 Runnable
 * 2 由于实现的方式天然实现了共享数据，在多个线程共享数据的情况下，使用 Runnable 更有优势。
 * 
 * 而且根据源码上来看， Thread 其实就是实现了 Runnable  的类 ，本质上就是 Runnable 实现的类。
 * 
 * 相同点
 * 都需要重写run()方法，把方法的逻辑写在里面。都需要start进行创建线程。
 * 
 */
public class RunnableTest {
    public static void main(String[] args) {
        // 3 实现类的对象
        RunThread rThread = new RunThread();
        // 4 对象作为参数传递到 Thread 类的构造器里面，创建 Thread 的对象
        Thread t1 = new Thread(rThread);
        t1.setName("线程1");
        t1.start();// 5 启动

        // 这里关于start为什么能调用run()方法做一个解释。
        // 看源码可以发现， rThread 这个 Thread 构造器因为是里面有一个属性 target
        // public Thread(Runnable target)
        // target 本身就是一个 private Runnable target; Runnable 对象类型的
        // 而Runnable 自带run()方法的实现

        // 上面就相当于把 rThread 这个对象 赋值到了 target 属性

        Thread t2 = new Thread(rThread);
        t2.setName("线程2");
        t2.start();
    }
}

// 1 创建了一个实现了 Runnable 接口的类
class RunThread implements Runnable {
    // 2 实现类去实现 Runnable 中的抽象方法run
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }
}