package basis;

/**
 * break 和 continue 区别 break 使用范围 1 switch-case 2 循环结构（结束当前循环） continue 使用范围 1
 * 循环结构（结束当次循环） 相同点 后面都不能执行语句
 */

public class BreakContinue {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            if (i % 4 == 0) {
                break;
            }
            System.out.println(i); // 123
        }

        // ***************** */

        for (int i = 1; i < 11; i++) {
            if (i % 4 == 0) {
                continue;
            }
            System.out.println(i); // 1235678910
        }

        // ***************** */
        label: for (int i = 1; i < 4; i++) {

            for (int j = 1; j < 10; j++) {
                if (j % 4 == 0) {
                    // break; // 跳出当前最近的循环
                    // continue; // 结束当次循环 skip
                    break label; // 结束指定 label 循环 j 1j 2j 3
                    // continue label; //结束指定 label 循环 j 1j 2j 3j 1j 2j 3j 1j 2j 3
                }
                System.out.print("j " + j);
            }
            System.out.println();
        }

    }

}