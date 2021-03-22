package com.design.pattern.proxy.dynamic;
/**
 * 动态代理
 * 老师类
 */
public class Teacher implements ITeacher {

    @Override
    public void teach() {
        System.out.println("真正的老师教学");
        
    }

    @Override
    public String sayHello(String name) {
        return "SayHello to " + name; 
    }
    
}
