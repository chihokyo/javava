package com.stringclass.com;
/**
 * 
 * StringBuffer的常用方法：
 * 
 * 
 * StringBuffer append(xxx)：提供了很多的append()方法，用于进行字符串拼接
 * StringBuffer delete(int start,int end)：删除指定位置的内容
 * StringBuffer replace(int start, int end, String str)：把[start,end)位置替换为str
 * StringBuffer insert(int offset, xxx)：在指定位置插入xxx
 * StringBuffer reverse() ：把当前字符序列逆转
 * public int indexOf(String str)
 * public String substring(int start,int end):返回一个从start开始到end索引结束的左闭右开区间的子字符串
 * public int length()
 * public char charAt(int n )
 * public void setCharAt(int n ,char ch)
 * 
 * 增 append（） 连续调用的方法链问题
 * 删 delete(int start,int end)
 * 改 setCharAt(int n ,char ch) / replace(int start, int end, String str)
 * 查 charAt(int n )
 * 插入 insert(int offset, xxx)
 * 遍历
 */
public class StringBufferTest {
    public static void main(String[] args) {
        
        StringBuffer sb1 = new StringBuffer("abc111");
        sb1.append("hello");
        System.out.println(sb1); // abc111hello
        sb1.delete(1, 2);
        System.out.println(sb1); // ac111hello
        sb1.replace(1, 3, "xyz");
        System.out.println(sb1); // axyz11hello
        sb1.reverse();
        System.out.println(sb1); // olleh11zyxa

        // 有返回值的
        // StringBuffer sb2 = sb1.substring(4,7); NG 返回类型必须是 String
        String s2 = sb1.substring(4);
        System.out.println(s2); // h11zyxa

        // 关于方法链
        sb1.append("bb").append(false).append('3');
        System.out.println(sb1); // olleh11zyxabbfalse3


    }
}
