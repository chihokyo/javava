package com.threadexer.java;

import java.util.concurrent.locks.ReentrantLock;
/**
 *  解决线程安全问题的方式3 Lock 锁 ---JDK5新增
 * 
 * 1 使用方法
 *  a 实例化ReentrantLock
 *  b 调用上锁方法lock()
 *  c 调用解锁方法unlock()
 * 
 * 2 synchronized PK lock
 *      相同点 都能解决安全问题
 *      不同点 synchronized 机制在完全执行完代码后，自动释放同步资源，但是lock需要手动释放
 * 
 * 3 线程安全优先使用顺序。
 *      Lock → 同步代码块（已经进入了方法体，分配了相应资源） → 同步方法（在方法体之外）
 * 为什么优先使用lock呢，是因为更加灵活，可以控制。可以节省jvm的资源。
 */
public class TicketThreadLock {
    public static void main(String[] args) {

        TicketThreadLockWindow tlw = new TicketThreadLockWindow();
        Thread t1 = new Thread(tlw);
        Thread t2 = new Thread(tlw);
        Thread t3 = new Thread(tlw);

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketThreadLockWindow implements Runnable {

    private int tickets = 100;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (tickets > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":卖票，票号是:" + tickets);
                    tickets--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}