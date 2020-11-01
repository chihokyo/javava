package com.threadexer.java;

/**
 * 单例模式-懒汉式 出现的线程安全问题
 */

public class SingletonByThread {
    public static void main(String[] args) {
        
    }
}

class BankTest {

    // 单例模式
    private BankTest(){}
    private static BankTest instance = null;

    //*************************** */
    // 方式1 效率稍差 ===>>> 因为在第一次做完判断之后，其实第二次之后根本就不需要判断了

    // 如果多个线程进来之后进行判断可能会出现线程安全问题
    // 同步监视器 ：BankTest.class
    // public static synchronized BankTest getInstance() {
    //     if (instance == null) {
    //         // 在这里会出现阻塞，另一个线程也进入null这个判断里面
    //         // 出现线程问题的原因 
    //         // 1 共享数据 BankTest instance
    //         // 2 2个线程
    //         // 因为判断是否null 和  赋值 这都属于对于共享数据的操作
    //         instance = new BankTest();
    //     }
    //     return instance;
    // } 

    //*************************** */
    // 方式2
    public static BankTest getInstance() {
        // 这里提前进行判断就不会出现多余的判断
        if (instance == null) {
            synchronized(BankTest.class){
                if (instance == null) {
                    instance = new BankTest();
                }
            }
        }

        return instance;
    }

}
