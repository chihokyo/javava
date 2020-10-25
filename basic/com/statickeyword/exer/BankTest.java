package com.statickeyword.exer;
/**
 * 关于static类的银行相关小练习
 * 
 *  编写一个类实现银行账户的概念，包含的属性有“帐号”、“密码”、“存款余额”、“利率”、“最小余额”，
 *  定义封装这些属性的方法。账号要自动生成。
 *	编写主类，使用银行账户类，输入、输出3个储户的上述信息。
 *	考虑：哪些属性可以设计成static属性。
 */
public class BankTest {
    public static void main(String[] args) {
        Bank b1 = new Bank("123456", 1000);
        Bank b2 = new Bank("123456", 2000);

        Bank.setInterestRate(0.012);
        Bank.setMinMoney(100);

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(Bank.getInterestRate());
        System.out.println(Bank.getMinMoney());

    }
}

class Bank {
    private int id;
    private String pwd = "000000";
    private double balance;

    private static double interestRate;
    private static double minMoney = 1.0;

    private static int init = 1001; // 自动生成

    // 初始化的时候就进行对id进行赋值
    public Bank() {
        id = init++;
    }

    public Bank(String pwd, double balance) {
        id = init++;
        this.pwd = pwd;
        this.balance = balance;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public static double getInterestRate() {
        return interestRate;
    }

    //  属性是static ，那么相应的get和set也应该是
    public static void setInterestRate(double interestRate) {
        Bank.interestRate = interestRate;
    }

    public static double getMinMoney() {
        return minMoney;
    }

    public static void setMinMoney(double minMoney) {
        Bank.minMoney = minMoney;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    // 重写这个方法自动输出相应信息
    @Override
    public String toString() {
        return "Account [id=" + id + ", pwd=" + pwd + ", balance=" + balance + "]";
    }
}
