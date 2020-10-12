/**
 * 三元运算符 表达式结果为Boolean类型
 * Conditional operator
 * 表达式1和表达式2最后结果要求是统一的
 * (m > n) ? 3 : "xx"; 错误
 * 三元运算符都可以改写成 if-else
 * 但是 if-else 不一定都能写成三元运算符的形式
 */

public class ConditionalOperator {
    public static void main(String[] args) {
        // max类型取决于表达式的结果m或者n
        // m,n 最后必须为统一的类型
        int m = 15;
        int n = 15;
        int max = (m > n) ? m : n;
        System.out.println(max);

        // ***************************
        String maxStr = (m > n) ? "m大" : "n大";
        System.out.println(maxStr);

        // ***************************
        String maxStr2 = (m > n) ? "m大" : (m == n ? "m和n相等" : "n大");
        System.out.println(maxStr2);

        // ***************************
        // 获取3个数字最大值
        int n1 = 12;
        int n2 = 30;
        int n3 = -52;
        // 方法1
        int max2 = (n1 > n2) ? n1 : n2;
        int max3 = (max2 > n3) ? max2 : n3;
        System.out.println("3个数字中最大的是：" + max3);
        // 方法2 嵌套 (max2 > n3) ? max2 : n3; 中的max2进行嵌套
        int max4 = (((n1 > n2) ? n1 : n2) > n3) ? ((n1 > n2) ? n1 : n2) : n3;
        System.out.println("3个数字中最大的是：" + max4);
    }
}
