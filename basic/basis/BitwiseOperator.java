package basis;

/**
 * 位运算符 Bitwise operator 位运算符号和逻辑运算符符号有部分重合 如何判断呢，那么就要根据前后的数据类型进行判断。 如果是 boolean
 * ： 逻辑运算符 如果是 数值： 位运算符 1. 位运算符操作的都是整型的数据 2. << ：在一定范围内，每向左移1位，相当于 * 2 >>
 * :在一定范围内，每向右移1位，相当于 / 2
 */

public class BitwiseOperator {
    public static void main(String[] args) {
        // 左移
        System.out.println(3 << 2);
        // 0000 0011 左移 2个 0000 1100 
        // 就相当于 3 * 2**2 = 14
        // 左移一位，相当于2*几次方
        System.out.println(3 >> 1);

        int m = 12;
		int n = 5; 
		System.out.println("m & n :" + (m & n)); // 4
		System.out.println("m | n :" + (m | n)); // 13
		System.out.println("m ^ n :" + (m ^ n)); // 9

        /******** 练习1**/
        // 如何交换2个值

        // 方法1 临时变量 
        // 需要临时变量 但无数据类型限制
        int num1 = 10;
        int num2 = 20;
        int temp = num1;
        num1 = num2;
        num2 = temp;
        System.out.println("num1: " + num1 + " ,num2: " + num2);

        // 方法2 临时变量
        // 1存储范围会超过 2数据类型有限制（只能是数值型）
        num1 = num1 + num2;
        num2 = num1 - num2;
        num1 = num1 - num2;
        System.out.println("num1: " + num1 + " ,num2: " + num2);

        // 方法3 位运算
        // 先上个公式吧 m =k^n = (m^n)^n
        // 只能适用于数值类型
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;
        System.out.println("num1: " + num1 + " ,num2: " + num2);

    }
}
