package com.stringclass.com;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
/**
 * String类和其他类型的转换
 * 
 * String类 转换到 基本数据类型 or 包装类
 * 
 * 基本数据类型 or 包装类 转换到 String
 * 
 * *******************************************
 * 
 * 
 * 
 * 新知识 String 与 char[]
 * 
 * String -----> char[] 
 *      char[] str4Array = str4.toCharArray();
 * 
 * 
 * char[] -----> String
 * 
 *      char[] str5Array = new char[]{'n','b','a', '1'}; String str5 = new
 *      String(str5Array);
 * 
 * 新知识 String 与 byte[]
 * 
 * 编码 字符串 → 字节 （看得懂 === 计算机看得懂）
 * 解码 字节 → 字符串 
 * 
 * String -----> byte[] 调用 String getBytes()
 * 
 * byte[] -----> String
 * 
 * 
 * 
 */
public class StringConvert {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 1 调用包装类的静态方法 parseXX(str);
        String str1 = "1234";
        // int num = (int)str1 错误，根本不是字符类关系，无法进行强转
        int num = Integer.parseInt(str1);
        System.out.println(num); // 1234

        // 2 调用String重载的valueOf(ddd)
        // String str2 = String.valueOf(num);
        String str3 = num + ""; // heap里面 只要 ”“
        System.out.println(str1 == str3); // false 一个在堆里，一个在字符串常量池里

        // 3 String ---> char[]
        String str4 = "abc124";
        char[] str4Array = str4.toCharArray();
        for (int i = 0; i < str4Array.length; i++) {
            System.out.println(str4Array[i]); // abc124
        }

        // 4 char[] ---> String
        char[] str5Array = new char[] { 'n', 'b', 'a', '1' };
        String str5 = new String(str5Array);
        System.out.println(str5); // nba1

        // 5 String ---> byte[]

        String str6 = "nba1166中";
        byte[] str6Bytes = str6.getBytes();
        byte[] str6BytesGBK = str6.getBytes("gbk");
        System.out.println(Arrays.toString(str6Bytes)); // [110, 98, 97, 49, 49, 54, 54, -28, -72, -83] ascii码
                                                        // 使用默认utf-8的码
        System.out.println(Arrays.toString(str6BytesGBK)); // [110, 98, 97, 49, 49, 54, 54, -42, -48] ascii码 使用默认utf-8的码

        // 6 byte[] ---> String

        String str7 = new String(str6Bytes);
        System.out.println(str7); // nba1166中

        String str8 = new String(str6BytesGBK);
        System.out.println(str8); // nba1166�� 出现乱码，因为编码字符集和解码字符集的问题。上面进行了指定，所以这里也要进行指定
        String str9 = new String(str6BytesGBK, "gbk");
        System.out.println(str9); // nba1166中

    }
}
