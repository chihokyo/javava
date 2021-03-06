package com.enumclass.exer;
/**
 * 1 使用enum关键字定义的枚举类
 *      默认继承的是 java.lang.Enum 类
 * 
 * 2 枚举类常用方法
 *  values()方法：返回枚举类型的对象数组。该方法可以很方便地遍历所有的枚举值。
 *  valueOf(String str)：可以把一个字符串转为对应的枚举类对象。要求字符串必须是枚举类对象的“名字”。
 *  toString()：返回当前枚举类对象常量的名称
 * 
 * 3 使用enum关键字定义的枚举类实现接口的情况
 *   情况一：实现接口，在enum类中实现抽象方法
 *   情况二：让枚举类的对象分别实现接口中的抽象方法
 */
public class EnumerationAfter {
    public static void main(String[] args) {
        SeasonEnum summer = SeasonEnum.SUMMER;
        System.out.println(summer); // SUMMER
        System.out.println(summer.toString()); // SUMMER
        // 这里的Enum继承的不是 Object 类，继承的是 java.lang.Enum Enum的类
        // 而 toString方法是被重写了的 正常的 Object 类打印出来的是地址
        // public String toString() {
        //     return name;
        // }

        System.out.println(SeasonEnum.class.getSuperclass()); // class java.lang.Enum

        // 常用类的方法 values() 枚举类到底有几个状态
        SeasonEnum[] values = SeasonEnum.values();
        for (int i = 0; i < values.length; i++) {
            System.out.println(values[i]);
            values[i].show(); // 每一个枚举类对象的实现抽象接口show()方法调用
        }

        Thread.State[] threadValues = Thread.State.values();
        for (int i = 0; i < threadValues.length; i++) {
            System.out.println(threadValues[i]);
        }

         // 常用类的方法 valueOf(String objName) 返回枚举类中对象名是objName的对象
         SeasonEnum w = SeasonEnum.valueOf("WINTER");
         System.out.println(w); // WINTER
        //  SeasonEnum w1 = SeasonEnum.valueOf("WINTER1"); NG
        //  System.out.println(w1); // java.lang.IllegalArgumentException:
        w.show(); // 实现了一个接口的方法show()
    }
}
interface Info {
    void show();
}

enum SeasonEnum implements Info {

    // 自定义的时候重复的代码提出掉，提供当前枚举类的对象。多个对象之间用,隔开，最后；
    SPRING("春", "春暖花开"){
        @Override
        public void show() {
            System.out.println("针对枚举类每一个对象的show()实现 春");
        }
    },
    SUMMER("夏", "夏日炎炎"){
        @Override
        public void show() {
            System.out.println("针对枚举类每一个对象的show()实现 夏");
        }
    },
    AUTUMN("秋", "秋高气爽"){
        @Override
        public void show() {
            System.out.println("针对枚举类每一个对象的show()实现 秋");
        }
    },
    WINTER("冬", "冰冷刺骨"){
        @Override
        public void show() {
            System.out.println("针对枚举类每一个对象的show()实现 冬");
        }
    };

    // 既不能被随意修改，也不能被继承。如果不是私有，那么外部就可以随便new 那就不是确定的了
    private final String seasonName;
    private final String seasonDesc;

    private SeasonEnum(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }
    public String getSeasonName() {
        return this.seasonName;
    }

    public String getSeasonDesc() {
        return this.seasonDesc;
    }

    // // 一般不去重写
    // @Override
    // public String toString() {
    //     return "Season{" + "seasonName='" + seasonName + '\'' + ", seasonDesc='" + seasonDesc + '\'' + '}';
    // }
    
    // 方式1：抽象方法的实现 方式2参考上面枚举类对象操作
    // @Override
    // public void show() {
    //     System.out.println("实现了一个接口的方法show()");
    // }
    
}

