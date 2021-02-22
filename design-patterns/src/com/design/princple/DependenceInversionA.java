package com.design.princple;

public class DependenceInversionA {
    public static void main(String[] args) {
        PersonA p = new PersonA();
        p.recieve(new EmailA()); // Email: Hello World
    }
}

class EmailA {
    public String getInfo() {
        return "Email: Hello World";
    }
}

// 方式1 分析
// 1 简单
// 2 但是如果这个时候接收的只是Email 有可能是微信 短信等等呢？
// 这样就会无限增加类 Person也要增加方法
// 3 解决思路：引入一个抽象接口，IReceiver 表示接收者
// Person 和 IReceiver 就是在一起有依赖
class PersonA {
    public void recieve(EmailA email) {
        System.out.println(email.getInfo());
    }
}