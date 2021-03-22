package com.design.pattern.flyweight;
/**
 * 网站
 */
public abstract class WebSite {
    // 这里聚合了一个User类
    // 作为外部状态 是有变化的 比如形式上只有weibo和wechat 
    // 但是里面的用户作为外部状态是经常变化的
    public abstract void use(User user);
}
