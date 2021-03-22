package com.design.pattern.flyweight;

public class ClientMain {
    public static void main(String[] args) {
        // 新建一个池子
        WebSiteFactory factory = new WebSiteFactory();

        WebSite site1 = factory.getWebSiteCategory("Wechat");
        WebSite site2 = factory.getWebSiteCategory("Weibo");
        WebSite site3 = factory.getWebSiteCategory("Weibo");
        
        System.out.println(factory.getWebSiteCount()); // 2 

        // 虽然weibo是2 ，但是可以拥有不同的外部状态。也就是User

        site1.use(new User("AA"));
        site2.use(new User("BB"));
        site3.use(new User("CC"));
        
        System.out.println(factory.getWebSiteCount()); // 2 

    }
}
