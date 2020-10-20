package com.inheritancetest;

public class KidsTest {
    public static void main(String[] args) {
        Kids someKid = new Kids(12);

        someKid.printAge();

        // 以下是父类继承过来
        someKid.setSex(1);
        someKid.setSalary(1000);

        someKid.employeed();
        someKid.manOrWoman();

        // 这个时候内存结构
        // stack someKid 变量
        // heap 父类所有的方法属性都有
    }
}
