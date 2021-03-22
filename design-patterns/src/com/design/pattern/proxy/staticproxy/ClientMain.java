package com.design.pattern.proxy.staticproxy;
/**
 * 静态代理-执行Main
 */
public class ClientMain {
    public static void main(String[] args) {
        // 新建一个被代理类 也就是老师
        Teacher target = new Teacher();
        // 创建一个被代理类 也就是中介机构
        TeacherProxy teacherProxy = new TeacherProxy(target);

        // 看似是中介机构开始教学
        // 其实真正教学的是老师
        // 最后调用的是代理类
        teacherProxy.teach();
    }
}
