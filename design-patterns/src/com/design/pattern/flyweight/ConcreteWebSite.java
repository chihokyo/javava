package com.design.pattern.flyweight;

public class ConcreteWebSite extends WebSite {

    // 共享的部分 内部状态
    // 网站发布的类型
    private String type = "";

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println(type + ":"+ user.getName()); 
    }
}
