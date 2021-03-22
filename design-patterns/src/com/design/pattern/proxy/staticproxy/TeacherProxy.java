package com.design.pattern.proxy.staticproxy;

/**
 * 静态代理-代理类 
 * 中介机构
 */
public class TeacherProxy implements ITeacher {

    private ITeacher target;

    // 这里相当于使用构造器 对 ITeacher 需要代理的类进行一个初始化
    // 把需要代理的实例传进来
    // 目标对象 通过接口来聚合
    public TeacherProxy(ITeacher target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("中介机构开始");
        // 这里实际教学的其实是真正的老师，因为上面的构造器传入的就是真正的老师
        target.teach();
        System.out.println("中介机构结束");
    }
}
