package com.threadexer.java;
/**
 * 测试 Thread 类常用方法 
 * 1 start 启动当前线程；调用当前线程run()方法 
 * 2 run() 通常需要重写 Thread 类的此方法。方法体内写入线程的操作。 
 * 3 currentThread() 静态方法 返回当前线程 
 * 4 getName() 
 * 5 setName() 起名要在start之前哦 
 * 6 yield() 释放当前CPU线程的执行权 但速度很快也不一定就切换到下一个
 * 7 join()  在线程a中调用b的join(),a就进入到阻塞状态。直到线程b完全执行之后，a才结束阻塞状态。
 * 8 stop() 强制结束线程生命周期 → 已经被取消了
 * 9 sleep(long millis) 毫秒级别 让当前线程阻塞进入睡眠，直到结束后CPU重新分配
 * 
 * 【关于优先级】
 * 1 MIN_PRIORITY 1
 * 2 MAX_PRIORITY 10
 * 3 NORM_PRIORITY 5
 * 设置，setPriority()
 * 获取，getPriority()
 * 并非高的优先级就一定比低的优先级优先执行，只是从概率上来讲而已。90%和20%的概率，对比。
 * 只是高优先级 抢占 低优先级 CPU 的 执行权。并非100%
 */
public class ThreadMethodTest {
    public static void main(String[] args) {
        // 命名的方式 1 构造器 2 setName()
        ThreadMethodTestA t1 = new ThreadMethodTestA("构造器实现的线程名");
        // t1.setName("线程1: ");

        // 设置分线程的优先级
        t1.setPriority(Thread.MAX_PRIORITY);

        t1.start();

        // 给主线程命名
        Thread.currentThread().setName("主线程: ");
        // 设置主线程的优先级
        Thread.currentThread().setPriority(Thread.MIN_PRIORITY);

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "优先级:"+ Thread.currentThread().getPriority() + ","+ i);
            }
            if (i == 20) {
                try {
                    // 插入本身对象线程直到执行完
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

class ThreadMethodTestA extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                // 只能写在这里，而不能在类里进行 throws 因为run的父类根本没有 抛出异常throws
                // 等待1秒之后也要等待CPU分配
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 2 == 0) {
                System.out.println(getName() + ",优先级:"+ getPriority() + "," + i);
            }

            if (i % 20 == 0) {
                // 释放当前CPU线程的执行权 但速度很快也不一定就切换到下一个 可能会被再分配
                Thread.yield();
                // this.yield(); 当前类的对象 也就是t1
                // 简写 Thread.currentThread() ==== this ===== t1
            }
        }
    }

    // 用的是重写override
    public ThreadMethodTestA(String name) {
        super(name);
    }
}