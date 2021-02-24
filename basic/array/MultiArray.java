/**
 * 多维数组（二位数组最多）
 * 按照上一个一维数组的定义，数组的类型必须是基本类型
 * 但是数组的元素可以是引用类型
 * 所以数组的元素如果是数组，那么就可以组成一个二维数组
 * 其实本质上是没有二维数组
 * 
 */
public class MultiArray {

    // 1 2维数组的声明&初始化
    // 2 如何调用指定位置的元素
    // 3 获取长度
    // 4 如何遍历
    // 5 数组元素的默认初始值 外层元素初始值是地址 内存元素和一维一样
    // 6 数组的内存解析

    public static void main(String[] args) {
        /*************************各种声明***********************/
        // 一维数组的静态声明初始化
        int[] singleArr = new int[]{0,1,2};
        // 二维数组的静态声明初始化
        int[][] twoArr = new int[][]{{11,2},{3,4,5},{88}};
        // 二维数组的动态声明初始化1
        String[][] twoArr2 = new String[3][2];
        // 这种感觉就是一个3行2列的感觉
        // 12，13
        // 1，89
        // 8，9
        // 二维数组的动态声明初始化2
        int[][] twoArr3 = new int[3][];
        // 错误案例：int[][] twoArr4 = new int[][2];
        // 错误案例2：int[3][4] twoArr4 = new int[][2];

        // 变化球 不建议写 放在类型后面or变量名后面都可以
        int arr[][] = new int[][]{{11,2},{3,4,5},{88}}; //
        int[] arr2[] = new int[][]{{11,2},{3,4,5},{88}};
        int[] arr3[] = {{11,2},{3,4,5},{88}};// 不标准也可以：类型推断
        // arr3 = {{11,2},{3,4,5},{88}};// 但这样不行 啥都没写，谁知道是啥
        /*************************尽量写标准形式***********************/

        // 2 如何调用指定位置的元素
        System.out.println("twoArr[1][1]: " + twoArr[1][1]); // 4
        System.out.println("twoArr2[1][1]: " + twoArr2[1][1]); // null
        // System.out.println("twoArr3[1][1]: " + twoArr3[1][1]); // 空指针报错！NullPointerException
        System.out.println("******华丽的分割线******");

        // 3 获取长度
        System.out.println("twoArr.length: " + twoArr.length); // 3
        System.out.println("twoArr[0].length: " + twoArr[0].length); // 2
        System.out.println("twoArr[1].length: " + twoArr[1].length); // 3
        System.out.println("******华丽的分割线******");

        // 4 如何遍历
        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                System.out.print(twoArr[i][j] + ",");
            }
            System.out.println();
        }
        System.out.println("******华丽的分割线******");

        // 5 数组元素的默认初始值
        // 和1维完全一样
        int[][] intTwoArr = new int[4][3];
        String[][] stringTwoArr = new String[3][1];
        double[][] doubleTwoArr = new double[4][];

        System.out.println(intTwoArr); // [[I@5451c3a8 内存地址
        System.out.println(intTwoArr[0]); // [I@5451c3a8 内存地址
        System.out.println(intTwoArr[0][0]); // 0

        // 解析一下
        // [I@5451c3a8 维度+类型+地址
        // [ 表示 一维的
        // @ 表示 地址
        // 5451c3a8 表示 16进制地址

        System.out.println(stringTwoArr[0]); // [Ljava.lang.String;@2626b418 内存地址
        System.out.println(stringTwoArr[0][0]); // null


        System.out.println(doubleTwoArr); // [[D@76ed5528
        System.out.println(doubleTwoArr[0][0]); // null

    }
    
}
