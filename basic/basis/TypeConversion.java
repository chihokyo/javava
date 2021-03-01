package basis;
/**
自动类型提升（boolean不行,其他7可以）
byte → short → int → long → float → double 层层递增
容量大的和容量小的做运算，结果自动提升到容量大的类型。
此时容量大小，表示数的范围大和小，而不是本身占用内存大小
※ 当byte char short 三种类型做运算时，结果都是int

 */

public class TypeConversion {
    public static void main(String[] args) {
        byte b1 = 2;
        int i1 = 129; // int 不能 转换到 byte，比如转换到了129 ，这样不行.
        int b2 = b1 + i1;// 范围大的数据类型
        System.out.println(b2);

        float f = b1 + i1;// 带小数点
        float d = f;// 带小数点
        System.out.println(f); // 131.0
        System.out.println(d); // 131.0

        // ****************特殊情况******************

        char c1 = 'c';
        int i3 = 10;
        int i4 = c1 + i3;
        System.out.println(i4);

        short s2 = 10;
        //char c2 = c1 + s2; // 编译不通过（是int）

        byte b3 = 10;
        System.out.println(b3);
        // char c3 = c1 + b3;// 编译不通过（是int）

        // short s3 = b3 + s2;

        // ****************************************
    }
}
