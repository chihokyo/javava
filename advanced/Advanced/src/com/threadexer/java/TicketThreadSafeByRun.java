package com.threadexer.java;
/**
 * 同步方法实现 Runnable 接口线程安全
 * synchronized 关键字修饰的方法就是同步的
 * 同步方法 有没有同步监视器呢 是有的
 */
public class TicketThreadSafeByRun {
    public static void main(String[] args) {
        
        TicketThreadSafeByRunWindow tRun = new TicketThreadSafeByRunWindow();

        Thread t1 = new Thread(tRun);
        t1.setName("窗口1");
        t1.start();

        Thread t2 = new Thread(tRun);
        t2.setName("窗口2");
        t2.start();

        Thread t3 = new Thread(tRun);
        t3.setName("窗口3");
        t3.start();
    }
}

class TicketThreadSafeByRunWindow implements Runnable {

    private int tickets = 100;

    // public synchronized void run() { NG 因为这样包裹的代码多了
    // 所以run在这个问题上不适合
    public void run() {
        while (true) {
            show();
        }
    }
    
    // // 同步代码块
    // public void show() {
    //     synchronized(this){
    //         if (tickets > 0) {
    //             try {
    //                 Thread.sleep(100);
    //             } catch (InterruptedException e) {
    //                 e.printStackTrace();
    //             }
    //             System.out.println(Thread.currentThread().getName() + ":卖票，票号是:" + tickets);
    //             tickets--;
    //         }
    //     }
    // }

    private synchronized void show() { // 同步监视器 this
        if (tickets > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":卖票，票号是:" + tickets);
            tickets--;
        }
    }
}