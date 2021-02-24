package com.stringclass.com;
/**
 * 关于 StringBuffer 和 StringBuilder 的使用
 * 
 * String 不可变的字符序列
 * StringBuffer 可变的字符序列 + 线程安全
 * StringBuilder 可变的字符序列 + 非线程安全
 * 
 * jdk9之后从 char[] 改为了 byte[] 省空间
 * 
 * StringBuffer 给了一个16字符数组 长度就是16
 * public StringBuffer(int capacity) {
        super(capacity);
    }
    数组本身是没有变，但是内容是可变的。
 * 
 * 关于三者的区别
 * https://blog.csdn.net/csxypr/article/details/92378336
 * 
 * 如果需要对一个字符串频繁的进行修改，那么效率上肯定不会选择不可变的 String
 * 想如果避免去扩容 StringBuffer（30）指定容量
 * 
    String、StringBuffer、StringBuilder三者的异同？
    String:不可变的字符序列；底层使用char[]存储
    StringBuffer:可变的字符序列；线程安全的，效率低；底层使用char[]存储
    StringBuilder:可变的字符序列；jdk5.0新增的，线程不安全的，效率高；底层使用char[]存储

    源码分析：
    String str = new String();//char[] value = new char[0];
    String str1 = new String("abc");//char[] value = new char[]{'a','b','c'};

    StringBuffer sb1 = new StringBuffer();//char[] value = new char[16];底层创建了一个长度是16的数组。
    System.out.println(sb1.length());//
    sb1.append('a');//value[0] = 'a';
    sb1.append('b');//value[1] = 'b';

    StringBuffer sb2 = new StringBuffer("abc");//char[] value = new char["abc".length() + 16];

    //问题1. System.out.println(sb2.length());//3
    //问题2. 扩容问题:如果要添加的数据底层数组盛不下了，那就需要扩容底层的数组。
            默认情况下，扩容为原来容量的2倍 + 2，同时将原有数组中的元素复制到新的数组中。
            指导意义：开发中建议大家使用：StringBuffer(int capacity) 或 StringBuilder(int capacity)

            【以上char[]已经在jdk9里改成了byte[]】
 * 
 */
public class StringBufferBuilderTest {
    public static void main(String[] args) {

        StringBuffer sb1 = new StringBuffer("Abc");
        sb1.setCharAt(0, 'm'); // 因为是可变的，所以sb1就是变了
        System.out.println(sb1.length()); // 3

        sb1.append('s');
        sb1.append('b');
        System.out.println(sb1); // mbcsb
        System.out.println(sb1.length()); // 5

        // length 返回的不是value的长度，而是数组本身count多少 不是16。
        StringBuffer sb2 = new StringBuffer();
        System.out.println(sb2.length()); // 0
        sb2.append('b');
        sb2.append('z');
        System.out.println(sb2);

    }
}
