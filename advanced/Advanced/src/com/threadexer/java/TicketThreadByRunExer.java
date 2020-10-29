package com.threadexer.java;

/**
 * 简易买票系统（非完全瑕疵版本） 使用 Runnable 接口实现
 * 
 * 1 天然共享数据
 * 因为 TicketRunnable tRun = new TicketRunnable(); 本质上只创建了一个对象
 * 如果又增加了 TicketRunnable tRun2 = new TicketRunnable(); 那就没办法了。还是多个。
 * 
 */
public class TicketThreadByRunExer {
    public static void main(String[] args) {

        TicketRunnable tRun = new TicketRunnable();

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

class TicketRunnable implements Runnable {

    private int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + ":卖票，票号是:" + tickets);
                tickets--;
            } else {
                break;
            }
        }
    }
}
