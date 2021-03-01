/**
 * 数组基本概念 
 * 数组本身是引用数据类型 类、数组、接口 
 * 数组虽然是引用数据类型，数组的元素可以是基本数据类型or引用数据类型
 * 开闭连续的空间。
 * 开头就是地址（哇哦，好像c）
 * 还有不是连续的空间，那就是链表。 
 * 长度一旦确定，不能修改。
 */

public class OriginTest {

    // 1 1维数组的声明&初始化
    // 2 如何调用指定位置的元素
    // 3 获取长度
    // 4 如何遍历
    // 5 数组元素的默认初始值
    // 6 数组的内存解析

    public static void main(String[] args) {
        // int num; // 声明
        // num = 10; // 初始化
        // int id = 100; // 声明 + 初始化

        // /***** 1 静态初始化 :数组初始化和赋值操作【同时】进行*****/
        // int[] ids;// 声明 元素 int型数组
        // ids = new int[]{1001,1002,1003,1004};// 初始化

        /***** 1 动态初始化 :数组初始化和赋值操作【分开】进行 *****/
        String[] names = new String[5];

        // 一旦初始化完成，长度基本确定

        /***** 2 调用指定位置 *****/
        names[0] = "hello0";
        names[1] = "hello1";
        names[2] = "hello2";
        names[3] = "hello3";
        names[4] = "hello4";

        /***** 3 获取长度 *****/
        System.out.println(names.length);

        /***** 4 如何遍历 *****/
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }

        /***** 5 数组元素的默认初始值 *****/
        // 貌似官网也有写
        // https://docs.oracle.com/javase/specs/jls/se8/html/jls-4.html#jls-4.12.5
        // 5-1 数组元素是整型，那么所有初始值都是 0
        int[] intArr = new int[4];
        for (int i = 0; i < intArr.length; i++) {
            System.out.println(intArr[i]);
        }
        System.out.println("******华丽的分割线******");
        short[] shortArr = new short[4];
        for (int i = 0; i < shortArr.length; i++) {
            System.out.println(shortArr[i]);
        }
        System.out.println("******华丽的分割线******");

        // 5-2 数组元素是浮点型，那么所有初始值都是 0.0
        float[] floatArr = new float[4];
        for (int i = 0; i < floatArr.length; i++) {
            System.out.println(floatArr[i]);
        }
        System.out.println("******华丽的分割线******");

        double[] doubleArr = new double[4];
        for (int i = 0; i < doubleArr.length; i++) {
            System.out.println(doubleArr[i]);
        }
        System.out.println("******华丽的分割线******");

        // 5-3 数组元素是char 0 or '¥u0000' 绝对不是'0'，默认值是0对应的字符
        char[] charArr = new char[4];
        for (int i = 0; i < charArr.length; i++) {
            System.out.println(charArr[i]);
        }
        if (charArr[0] == '\u0000') {
            System.out.println("yes");
        }
        System.out.println("******华丽的分割线******");

        // 5-4 数组元素是boolean 默认都是false 因为都是0 0：false 1：true
        boolean[] booleanArr = new boolean[4];
        for (int i = 0; i < booleanArr.length; i++) {
            System.out.println(booleanArr[i]);
        }
        System.out.println("******华丽的分割线******");

        // 5-5 数组元素是引用数据类型，先用String测试一下 默认都是null

        String[] StringArr = new String[4];
        for (int i = 0; i < StringArr.length; i++) {
            System.out.println(StringArr[i]);
        }
        if (StringArr[0] == null) {
            System.out.println("yes");
        }
        System.out.println("******华丽的分割线******");

        // 6 数组的内存解析
        // 内存规范 jvm 内存分配
        // 栈：stack 线性结构 瘦长 局部变量（类方法中储存）
        // 堆，heap（new出来的结构 对象，数组）
        // method area →常量池 静态域 etc
        int[] arr = new int[] { 1, 2, 4 };
        String[] stringArr = new String[4];
        stringArr[1] = "刘德华";
        stringArr[2] = "张学友";
        stringArr = new String[3];
        // 方法中的变量都是局部变量 arr 放在栈里面
        // new之后再堆里面
        // 上面一段代码的在内存中的执行顺序和空间结构是如何的呢
        // 1 在相应结构里开辟空间 栈堆什么的
        // 2 初始化默认值
        // 3 变量名字存储栈内存地址
        // 4 内存地址指向在堆里面的数据
        // 5 重新赋值就是重新开辟一个空间更改指针
    }
}
