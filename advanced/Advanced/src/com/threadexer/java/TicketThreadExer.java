package com.threadexer.java;
/**
 * 简易买票系统（非完全瑕疵版本） 使用 Thread 实现
 * 创建3个窗口，总共100张票
 * 存在问题2个问题
 * 输出顺序不一致
 * 票号会有重复现象 ======>>>>> 线程安全问题
 */
public class TicketThreadExer {
    public static void main(String[] args) {

        TicketWindow t1 = new TicketWindow();
        TicketWindow t2 = new TicketWindow();
        TicketWindow t3 = new TicketWindow();

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketWindow extends Thread {
    // 这里必须是全局变量，否则有几个对象就*几倍。就有300张票。
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.out.println(getName() + ":卖票， 票号是:" + tickets);
                tickets--;
            } else {
                break;
            }
        }

    }
}
