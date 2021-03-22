package com.design.pattern.flyweight;
/**
 * 网站工厂类
 * 根据需求 返回一个网站
 */

import java.util.HashMap;

public class WebSiteFactory {
    // 集合 充池子
    private HashMap<String, ConcreteWebSite> pool = new HashMap<>();

    // 根据网站的类型 返 回一个网站的实例
    // 先去池子里去找 没有就新建 有的话就直接返回那个网站
    public WebSite getWebSiteCategory(String type) {
        if (!pool.containsKey(type)) {
            pool.put(type, new ConcreteWebSite(type));
        }
        // 这里必须要做类型转换
        // 如果没有的话就用不了use的方法
        // return (WebSite)pool.get(type);
        return pool.get(type);
    }

    // 获取网站的分类（池子里多少个网站）
    public int getWebSiteCount() {
        return pool.size();
    }

}
