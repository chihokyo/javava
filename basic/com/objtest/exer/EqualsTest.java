package com.objtest.exer;

import java.util.Date;
/**
 * 关于 equals 和 == 区别
 * 
 *  == 运算符
 * 1 基本数据类型和引用数据类型变量都可以用
 * 2 基本数据类型 ： 保存的数据 是否相等（不一定同类型）
 * 3 引用数据类型 ：比较的是地址值是否相同
 * 
 * equals() 方法
 * 1 基本数据类型 变量肯定不能用，因为基本数据类型根本就不是类！！
 * 2 只适用于引用数据类型
 * 3 在Object定义中 equals() 和 == 的作用是一样的
 * 4 String Date File 包装类都重写了 equals() 的写法，重写以后，比较的不是2个引用地址是否相同
 * 而是2个实体对象是否相同
 * 5 如何重写
 * 为什么要重写呢，因为通常定义类的时候不是想要知道她的地址是否相同，而是想知道实体内容是否相同。
 *    5-1 手动写 ====>>> 自己写的low
 *    5-2 自动生成 ====>>> 这个写法比较好 
 */
public class EqualsTest {
    public static void main(String[] args) {
        int i = 10;
        int j = 10;
        double d = 10.0;
        /***** 基本类型还是可以比的，运算符基本都可以类型提升,除了 Boolean *****/
        System.out.println(i == j); // true
        System.out.println(i == d); // true 类型提升 int 提升到了double
        // boolean b = true;
        // System.out.println( i == b); NG

        char c = 10; // (此处代表asc码的数字就是10)
        System.out.println(i == c); // true

        char c1 = 'A'; // 相互转换
        char c2 = 65;
        System.out.println(c1 == c2); // true

        System.out.println("华丽的分割线***** 引用数据类型 == *****");

        CustomerEqualsTest cust1 = new CustomerEqualsTest("Amy", 20);
        CustomerEqualsTest cust2 = new CustomerEqualsTest("Amy", 20);

        System.out.println(cust1 == cust2); // false 对象地址都不一样（2个引用是否指向同一个对象实体）

        String str1 = new String("yes");
        String str2 = new String("yes");
        System.out.println(str1 == str2); // false string 也是对象

        System.out.println("华丽的分割线***** 引用数据类型 equals() *****");

        /***** 引用数据类型 equals() *****/

        // equals比较的是
        // 可以看出来这俩都不是一个方法，string进行重写了
        // Object 类中 equals() 方法本质和 == 是一样的 可以查看定义进行验证
        // public boolean equals(Object obj) {
        // return (this == obj);
        // }

        System.out.println(cust1.equals(cust2)); // false **重写之后** true
        System.out.println(str1.equals(str2)); // true
        Date d1 = new Date(363636363L);
        Date d2 = new Date(363636363L);
        System.out.println(d1.equals(d2)); // true (这个时候不是比较地址，而是比较实体内容是否相同)
        System.out.println(d1 == d2); // false

    }
}

class CustomerEqualsTest {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public CustomerEqualsTest() {

    }

    public CustomerEqualsTest(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    // 重写的原则：比较2个对象的实体内容是否相同

    @Override
    public boolean equals(Object obj) {
        // 地址都一样了，肯定是true
        // if (this == anObject) {
        // return true;
        // }

        // 看一下类型是否一致，类型不一致，就强行转换类型

        // if (anObject instanceof String) {
        // String aString = (String)anObject;
        // if (!COMPACT_STRINGS || this.coder == aString.coder) {
        // return StringLatin1.equals(value, aString.value);
        // }
        // }
        // return false;

        if (this == obj) {
            return true;
        }

        if (obj instanceof CustomerEqualsTest) {
            CustomerEqualsTest cust = (CustomerEqualsTest) obj;
            // // 这时候要对比 每个属性是否相同 注意name是一个字符串 String
            // if (this.age == cust.age && this.name.equals(cust.name)) {
            // return true;
            // } else {
            // return false;
            // }
            // 直接简写成
            return this.age == cust.age && this.name.equals(cust.name);
        }
        return false;
    }
}