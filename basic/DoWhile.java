/**
 * dowhile循环
 * 最重要的就是
 * dowhile： 上来就要执行循环体，然后在判断。
 * while： 是先判断，在执行循环体！！！！！】
 */

public class DoWhile {
    public static void main(String[] args) {
        int i = 0;
        int sum = 0;
        int count = 0;
        do {
            if (i % 2 == 0) {
                System.out.println(i);
                count++;
                sum += i;
            }
            i++;
        } while (i <= 100);

        System.out.println("总和是: " + sum);
        System.out.println("个数是: " + count);

    }
}
