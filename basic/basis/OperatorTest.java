package basis;

/**
 * 运算符 + - * / % ++ -- + 算数运算符
 */

public class OperatorTest {
    public static void main(String[] args) {

        /**************** 取商/运算类型转换相关 ****************/

        int num1 = 12;
        int num2 = 5;
        int result1 = num1 / num2;
        System.out.println(result1); // 2 取商

        int result2 = num1 / num2 * num2;
        System.out.println(result2); // 10 2 * 5 按照顺序

        double result3 = num1 / num2;
        // 相当于num1/num2得到的是整数int 2，前面的double相当于类型转换成了2
        System.out.println(result3); // 2.0

        double result4 = num1 / num2 + 0.0;
        System.out.println(result4); // 2.0 还是2.0

        double result5 = num1 / (num2 + 0.0);
        System.out.println(result5); // 2.4 ,因为其中的num2已经转换成了double

        double result6 = (double) num1 / num2;
        System.out.println(result6); // 2.4 强制类型转换

        double result7 = (double) (num1 / num2);
        System.out.println(result7); // 2.0 和result4同理。

        /**************** 取余%运算类型转换相关 *************/

        // 结果符合 跟 被模数符号 相同
        // %经常用来判断能否被除尽的情况

        int m1 = 12;
        int n1 = 5;
        System.out.println("m1 % n1 = " + m1 % n1); // m1 % n1 = 2

        int m2 = 12;
        int n2 = -5;
        System.out.println("m2 % n2 = " + m2 % n2); // m2 % n2 = 2

        int m3 = -12;
        int n3 = 5;
        System.out.println("m3 % n3 = " + m3 % n3); // m3 % n3 = -2

        int m4 = -12;
        int n4 = -5;
        System.out.println("m4 % n4 = " + m4 % n4); // m4 % n4 = -2

        /**************** ++ -- *************/
        // 前++ 后++
        // 无论前后都属于自增1

        // 前 ++ 先自增1，然后在运算（比如这代码是赋值）
        int a1 = 10;
        int b1 = ++a1; // a1自增1后赋值
        System.out.println("a1: " + a1 + "b1: " + b1);

        // 后 ++ 先运算，然后在自增1
        int a2 = 10;
        int b2 = a2++; // a2赋值给b2后自增
        System.out.println("a2: " + a2 + "b2: " + b2);
        // 下面就是没区别的
        int a3 = 10;
        a3++; // ++a3
        int b3 = a3;
        System.out.println("b3" + b3);

        // 前-- 后--
        // 和上面同理

        int aa = 10;
        int bb = --aa;
        System.out.println("aa: " + aa + "bb: " + bb);

        int aa1 = 10;
        int bb1 = aa1--;
        System.out.println("aa1: " + aa1 + "bb1: " + bb1);
    }

}

class SignTest {
    public static void main(String[] args) {
        int i1 = 10;
        int i2 = 20;

        int i = i1++; 
        System.out.println("i = " + i); // i = 10
        System.out.println("i1 = " + i1); // i1 = 11
        i = ++i1;
        System.out.println("i = " + i); // i = 12
        System.out.println("i1 = " + i1); // i1 = 12

        i = i2--;
        System.out.println("i = " + i); // i = 20
        System.out.println("i2 = " + i2); // i2 = 19

        i = --i2;
        System.out.println("i = " + i); // i = 18
        System.out.println("i2 = " + i2); // i2 = 18

    }
}
