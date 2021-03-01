package com.enumclass.exer;

/**
 * 一 关于枚举类的使用
 *      1 类的对象只有有限个，确定的，我们称之为枚举类。
 *      2 当需要一组确定的常量组，强烈建议使用枚举类
 *      3 如果枚举类只有1个对象，那么就是单例模式（一个类只有一个对象）
 * 二 如何定义
 *      jdk5.0 之前 自定义
 *      jdk5.0 之后 enum 关键字
 */

public class EnumerationBefore {
    public static void main(String[] args) {
        Season spring = Season.SPRING;
        System.out.println(spring); // Season{seasonName='春', seasonDesc='春暖花开'}
    }
}

// 自定义枚举类

class Season {
    // ① 声明Season对象的属性 使用 private final进行修饰

    // 既不能被随意修改，也不能被继承。如果不是私有，那么外部就可以随便new 那就不是确定的了
    private final String seasonName;
    private final String seasonDesc;

    // ② 私有化类的构造器，并给对象属性赋值。

    // 给私有化变量final初始化的方式有 显示赋值，代码块赋值，构造器赋值。为什么选择构造器
    // 如果按照前面2个方法进行初始化，那么以后final就不能根据你的类而改变了
    // 只有构造器可以自定义
    private Season(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    // ③ 提供当前枚举类的对个对象 public static final 不可以改，可以使用static直接调用
    public static final Season SPRING = new Season("春", "春暖花开");
    public static final Season SUMMER = new Season("夏", "夏日炎炎");
    public static final Season AUTUMN = new Season("秋", "秋高气爽");
    public static final Season WINTER = new Season("冬", "冰冷刺骨");

    // ④ 其他诉求1 获取枚举类对象的属性（既然private不给获取，那就写方法进行获取）
    public String getSeasonName() {
        return this.seasonName;
    }

    public String getSeasonDesc() {
        return this.seasonDesc;
    }

    // ⑤ 其他诉求2 重写toString
    @Override
    public String toString() {
        return "Season{" + "seasonName='" + seasonName + '\'' + ", seasonDesc='" + seasonDesc + '\'' + '}';
    }
}
