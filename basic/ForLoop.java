/**
 * 循环结构 满足条件，反复执行 
 * init statement 初始条件 
 * text exp 循环条件 boolean 
 * body statement 循环体
 * alter statement 迭代部分
 */

public class ForLoop {
    public static void main(String[] args) {

        // 循环4要素
        for (int i = 1; i <= 5; i++) {
            System.out.println("hello");
            System.out.println(i);
        }

        int num = 1;
        for (System.out.print('a'); num <= 3; System.out.print('c'), num++) {
            System.out.print('b');
        }

        // result: abcbcbc

        // **********遍历100以内的偶数******/
        // 定义域问题，上面的i是在for循环内
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
        }
        System.out.println(sum);

        // **********3,5,7倍数分别添加******/
        for (int i = 1; i <= 150; i++) {
            System.out.print(i);
            if (i % 3 == 0) {
                System.out.print("foo ");
            }
            if (i % 5 == 0) {
                System.out.print("biz ");
            }
            if (i % 7 == 0) {
                System.out.print("baz ");
            }

            System.out.println();
        }

        // 输出2个正整数m，n，求其最大公约数和最小公倍数/
        // 12 20最大公约数是4，最小公倍数是60
        // 思路：
        // 获取2个数中较小的值，然后进行一个个循环，找到最大的都能整除的。（也就是第一个，记得 break
        // 最大公约数
        int m = 120;
        int n = 88;
        int min = (m >= n) ? n : m;
        for (int i = min; i > 0; i--) {
            if (m % i == 0 && n % i == 0) {
                System.out.println(m + "和" + n + "的" + "最大公约数是：" + i);
                break;
            }
        }

        // 思路：
        // 获取2个中最大的值，然后对乘法内的范围进行整除。找到最小的都能整除的。（也就是第一个，记得 break
        // 最小公倍数
        int max = (m >= n) ? m : n;
        for (int i = max; i <= m * n; i++) {
            if (i % m == 0 && i % n == 0) {
                System.out.println(m + "和" + n + "的" + "最小公倍数是：" + i);
                break;
            }
        }

    }

}
