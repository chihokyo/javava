package com.stringclass.com;

public class StringMethodTest {
    public static void main(String[] args) {
        String s1 = "hEllojava";

        // 返回长度 public int length() {
        System.out.println(s1.length()); // 9
        // 返回索引的字符串 public char charAt(int index) {
        System.out.println(s1.charAt(2)); // l
        // System.out.println(s1.charAt(20)); //
        // java.lang.StringIndexOutOfBoundsException:
        // 判断是否为空
        System.out.println(s1.isEmpty()); // false
        String s2 = " ";
        String s3 = "";
        System.out.println(s2.isEmpty()); // false
        System.out.println(s3.isEmpty()); // true

        // 变小写
        String s4 = s1.toLowerCase(); //
        System.out.println(s4); // hellojava
        System.out.println(s1); // hEllojava 注意 s1本身是没有变的
        // 变大写
        String s5 = s1.toUpperCase();
        System.out.println(s5); // HELLOJAVA

        // 忽略前后空格
        String s6 = " heello   djd  dhdh";
        String s7 = s6.trim();
        System.out.println(s7); // heello djd dhdh

        // 比较是否相等
        String s8 = "heeloo";
        String s9 = "heeLOO";
        System.out.println(s8.equals(s9)); // false
        // 忽略大小写比较是否相等
        System.out.println(s8.equalsIgnoreCase(s9)); // true

        // 连接2个字符串
        String s10 = s8.concat("yesysys");
        System.out.println(s10); // heelooyesysys

        // 比较大小 实现的 Comparable 接口
        String s11 = "abc";
        String s12 = "abe";
        System.out.println(s11.compareTo(s12)); // -2 负数 就是当前更大 涉及字符串排序

        // 取得现有字符串的子串
        String s13 = "我是谁啊我是谁";
        String s14 = s13.substring(2); // 因为字符串不可变性
        String s15 = s13.substring(2, 5); // 因为字符串不可变性
        System.out.println(s14); // 谁啊我是谁 从index2 ~ 最后
        System.out.println(s15); // 谁啊我 左闭右开

        // 搜索位置相关

        String s16 = "Amdhyhhlo";
        boolean b1 = s16.endsWith("lo");
        System.out.println(b1); // true
        System.out.println(s16.startsWith("Amd")); // true
        System.out.println(s16.startsWith("Ama")); // false
        System.out.println(s16.startsWith("Amd", 2)); // false

        String ss16 = "hhlo";
        System.out.println(s16.contains(ss16)); // true

        System.out.println(s16.indexOf("hh")); // 第一次出现位置的索引
        System.out.println(s16.indexOf("hh", 7)); // 找不到 返回 -1

        // 计算某个字符串出现几次
        // 可以使用indexOf 比如找到了一次 然后index是6 然后以index为七点，长度为终点 再开始找

        String s17 = "Amdhyhhlohyllo";
        System.out.println(s17.lastIndexOf("hy")); // 9 从后向前 开始匹配，但是index还是从前开始数的

        // Q 什么时候 indexOf 和 lastIndexOf 结果一样
        // A 情况1 要么只有存在一个 str 情况2 要么不存在 -1

        String s18 = "东京家里蹲东京事务所";
        String s19 = s18.replace("东", "西方");
        System.out.println(s19); // 西方京家里蹲西方京事务所 有就全部替换

        String s20 = "123java45mysql67heelo89yes";
        // ^,|,$ 开头或者结尾是，全部替换成空
        String s21 = s20.replaceAll("\\d+", ",").replaceAll("^,|,$", "");
        System.out.println(s21); // java,mysql,heelo,yes

        String s22 = "123445";
        boolean matches = s22.matches("\\d+");
        System.out.println(matches); // true
        String tel = "080-00000000";
        boolean result = tel.matches("080-\\d{7,8}");
        System.out.println(result); // true

    }
}
