/**
 * Loop练习
 */

/*****输出1个字符，判断正数or负数 的个数 输入0退出程序**** */
// 因为循环次数并不确定，所以这时候是要在循环体内进行判断
// 这个时候最好使用的就是while true

import java.util.Scanner;

public class LoopTest {
    public static void main(String[] args) {
        int positiveNum = 0;
        int negativeNum = 0;
        System.out.println("请输入一个整数：");
        Scanner scan = new Scanner(System.in);

        while (true) {
            int number = scan.nextInt();
            if (number > 0) {
                System.out.println("是一个正数" + number);
                positiveNum++;
            } else if (number < 0) {
                System.out.println("是一个负数" + number);
                negativeNum++;
            } else {
                System.out.println("准备退出程序");
                break;
            }
        }
        System.out.println("输入正数的个数: " + positiveNum);
        System.out.println("输入负数的个数: " + negativeNum);

        /***** 打印星星 */
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4 - i; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < i + 1; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(" ");
            }

            for (int k = 0; k < 4 - i; k++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }

}
