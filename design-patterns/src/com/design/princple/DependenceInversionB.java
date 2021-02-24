package com.design.princple;

public class DependenceInversionB {
    public static void main(String[] args) {
        PersonB p = new PersonB();
        p.receive(new EmailB()); // Email....
        p.receive(new Wechat()); // Wechat....
    }
}

interface IReceiver {
    public String getInfo();
}

// 这个方式是EmailB
class EmailB implements IReceiver {
    @Override
    public String getInfo() {
        return "Email....";
    }
}

// 这个方式如果是Wechat
class Wechat implements IReceiver {
    @Override
    public String getInfo() {
        return "Wechat....";
    }
}

class PersonB {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}
