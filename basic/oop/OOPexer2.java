/**
 * 计算矩形
 */

public class OOPexer2 {
    public static void main(String[] args) {
        OOPexer2 test = new OOPexer2();
        test.method();
        System.out.println(test.method2());
        test.method3(7, 6);

    }

    // 输出一个矩形
    public void method() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }

    }

    // 输出一个矩形的面积
    public int method2() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
        return 10 * 8;
    }

    // 输出指定m n一个矩形的面积
    public void method3(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

}
