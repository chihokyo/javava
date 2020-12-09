package basis;

/**
 * 赋值运算符 Assignment operator
 */
public class AssignmentOperator {
    public static void main(String[] args) {
        // 一般赋值
        int i1 = 10;
        int j1 = 10;

        // 连续赋值
        int i2, j2;
        i2 = j2 = 10;

        // 共用一个int类型 
        int i3 = 10, j3 = 20;

        /*********************** */

        int num1 = 10;
        num1 += 2; // num1 = num1 + 2
        System.out.println(num1);

        // 区别
        
        short s1 = 10;
        s1 = s1 + 2; // 编译失败。int。因为会改变数据类型
        s1 += 2; // 编译成功。不会改变数据类型
        // 实现变量+2的操作
        // 方法1 num = num + 2
        // 方法2 num += 2 （recommend）
        // 最好使用上面的 自增 s1 += 2 因为不会改变数据类型

        // 实现变量+1的操作
        //  方法1：num = num + 1
        // 方法2 num += 1
        // 方法3 num++ （recommend）

        // 练习1
        int i = 1;
        i *= 0.1;
        System.out.println(i); // 0
        i++;
        System.out.println(i); // 1

        // 练习2
        int m = 2;
        int n = 3;
        n *= m++; // n = n * m++
        System.out.println(m); // 3
        System.out.println(n); // 6

        // 练习3
        int n1 = 10;
        n1 += (n1++) + (++n1); // n1= n1 + (n1++) + (++n1) ==>>> 10 + 10 + 12 =22
        System.out.println(n); 

    }
    
}
