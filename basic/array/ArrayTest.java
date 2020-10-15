/**
 * 数组常见算法练习
 */

public class ArrayTest {
    public static void main(String[] args) {
        /*************** 杨辉三角 ****************/
        // 1 声明初始二维数组
        int[][] yanghui = new int[10][];
        // 2 二维数组的赋值
        for (int i = 0; i < yanghui.length; i++) {
            yanghui[i] = new int[i + 1];

            // 2.1 首末元素赋值
            yanghui[i][0] = 1;
            yanghui[i][i] = 1;

            // 2.2 其他元素a 优化前
            // if (i > 1) {
            // for (int j = 1; j < yanghui[i].length - 1; j++) {
            // yanghui[i][j] = yanghui[i - 1][j - 1] + yanghui[i - 1][j];
            // }
            // }

            // 2.2 其他元素b 优化后
            for (int j = 1; j < yanghui[i].length - 1; j++) {
                yanghui[i][j] = yanghui[i - 1][j - 1] + yanghui[i - 1][j];
            }
        }
        // 3 遍历二维数组
        for (int i = 0; i < yanghui.length; i++) {
            for (int j = 0; j < yanghui[i].length; j++) {
                System.out.print(yanghui[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("******华丽的分割线******");

        /*************** 设计一个范围在1-30内的长度为6的随机赋值数组 ****************/
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (99 - 10 + 1) + 10);
        }
        // 最大值
        // 最小值
        // 平均数
        // 总和

        // 最大值
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (maxValue < arr[i]) {
                maxValue = arr[i];
            }
        }
        System.out.println("最大值是: " + maxValue);

        // 最小值
        int minValue = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (minValue > arr[i]) {
                minValue = arr[i];
            }
        }
        System.out.println("最小值是: " + minValue);

        // 总和
        int sumValue = 0;
        for (int i = 0; i < arr.length; i++) {
            sumValue += arr[i];
        }
        System.out.println("总和是: " + sumValue);

        // 平均数
        int aveValue = sumValue / arr.length;
        System.out.println("平均数是: " + aveValue);

        System.out.println("******华丽的分割线******");

    }
}