/**
 * 关于字符串运行
 * String可以跟其他8种做运算。运算只能是连接运算，结果还是  String
 * 声明使用一对双引号”“
 */

public class TypeConversion3 {
    public static void main(String[] args) {
        String s1 = "Hello";
        System.out.println(s1);

        String s2 = "a";
        String s3 = ""; // 空字符串可以
        // char c = ''; // 空 char 不行
        System.out.println(s2);
        System.out.println(s3);

        // *********判断string运算***************
        int number = 1111;
        String numberStr = "学号:";
        String info = numberStr + number; // 连接运算，看String类型区分算术运算还是连接运算
        System.out.println(info);

        boolean b1 = true;
        System.out.println(numberStr + b1);

        // ***********练习1*********************
        // 只要有一个string，结果就是纯连接

        char c = 'a'; // a:97 A:65
        int num = 10;
        String str = "hello";
        System.out.println(c + num + str); // 107hello
        System.out.println(c + str + num);// ahello10
        System.out.println(c + (num + str));// a10hello
        System.out.println((c + num) + str);// 107hello
        System.out.println(str + num + c);// hello10a


        // ***********练习2*********************
        // 打印一个 *  * （中间是一个tab）
        // 想着 运算or连接
        System.out.println("*   *"); // ok
        System.out.println('*' + '\t' + '*'); // NG 因为是char+char = int / int+char=int
        System.out.println('*' + "\t" + '*'); //ok
        System.out.println('*' + '\t' + "*"); // NG  char+char = int / int+string
        System.out.println('*' + ('\t' + "*"));// ok

    }
}
