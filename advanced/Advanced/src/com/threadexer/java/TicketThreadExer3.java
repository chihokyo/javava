package com.threadexer.java;
/**
 * 同步方法实现 Thread 类线程安全 synchronized
 * 1 同步方法仍然需要同步监视器，但无需显示声明
 * 2    非静态的同步方法，同步监视器是 ====>>>> this
 *      静态的同步方法，同步监视器是，====>>>> 当前类本身。
 */

public class TicketThreadExer3 {
    public static void main(String[] args) {

        TicketWindow3 t1 = new TicketWindow3();
        TicketWindow3 t2 = new TicketWindow3();
        TicketWindow3 t3 = new TicketWindow3();

        t1.setName("窗口1");
        t2.setName("窗口2");
        t3.setName("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketWindow3 extends Thread {

    private static int tickets = 100;

    public void run() {
        while (true) {
            show();
        }
    }

    // 1 增加 同步监视器关键字
    // 2 设置为静态方法
    private static synchronized void show() { // 这个同步监视器就是当前的唯一的类 TicketWindow3.class
        // private synchronized void show() { 这个时候错误，以为有t1 t2 t3 有多个对象
        if (tickets > 0) {
            // 这时候会发现因为里面有的方法不是静态的会报错
            // System.out.println(getName() + ":卖票， 票号是:" + tickets) NG
            System.out.println(Thread.currentThread().getName() + ":卖票， 票号是:" + tickets);
            tickets--;
        }
    }
}