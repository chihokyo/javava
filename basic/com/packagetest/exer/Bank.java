package com.packagetest.exer;

public class Bank {

    private Customer[] cusomers; // 存放客户数组
    private int numberOfCustomers; // 客户的数目

    public Bank() {
        // 先初始化客户，不然第一次调用会报错
        cusomers = new Customer[10];
    }

    // 添加客户
    public void addCustomer(String f, String l) {
        // 新建客户，增加信息，更新数组index 增加客户数目
        Customer cust = new Customer(f, l);
        // cusomers[numberOfCustomers] = cust;
        // numberOfCustomers++; 下面是简写 记住是后++
        // 为什么是后++ 先操作把0赋值给数组 在进行＋个数
        cusomers[numberOfCustomers++] = cust;
    }

    // 获取当前客户量
    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    // 获取指定位置客户
    public Customer getCustomer(int index) {
        // 1 index 空指针问题 因为index可以越界
        // 2 可能不越界，但是本身没有客户，index也没
        if (index >= 0 && index < numberOfCustomers) {
            return cusomers[index];// 这样NG
        }

        return null;
    }
}
