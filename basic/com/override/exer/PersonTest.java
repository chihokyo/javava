package com.override.exer;
/**
 * 方法重写
 * 【定义】子类继承父类，可以对父类方法进行覆盖/复写
 *      1 重写以后，当创建子类对象后，通过子类调用子/父类方法的【同名同参数】方法时，实际执行的是子类中的方法
 *      2 区分重载or重写 overload/override 貌似这完全不一样啊。
 *      3 子类权限 >= 父类权限 【父类如果方法已经是private 就不能进行重写了，因为都看不到了更别说重写】
 *      4 重写 子类 返回值类型 不能大于 父类
 *      5 重写 父类如果是void 子类也必须是 void
 *      6 子类 抛出的异常也不能大于父类
 *      7 开发中其实真正的重写就是把父类的完全复制一份，除了方法体重写一下就好了
 *      8 子类父类 同名同参数 要么static 要么都不是static【因为static不能被重写
 */
public class PersonTest {
    public static void main(String[] args) {

        // 继承如下
        Student stu =  new Student("IT");
        stu.eat();
        stu.walk(10);
        stu.study();
    }
}
