/**
 * 操作数据的工具类 
 * https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
 */

public class UtilArray {
    public static void main(String[] args) {
        // 1. boolean equals(int[]a int[a2]) 判断是否相等
        int[] arr = new int[] { 1, 2, 3, 4 };
        int[] arr2 = new int[] { 1, 2, 3, 4 };
        boolean isEquals = Arrays.equals(arr, arr2);
        System.out.println(isEquals);

        // 2. String toString 输出数组信息 遍历
        System.out.println(Arrays.toString(arr)); // [1, 2, 3, 4]

        // 3. 替换数组
        Arrays.fill(arr, 100);
        System.out.println(Arrays.toString(arr)); // [100, 100, 100, 100]

        // 4. 排序

        int[] arr3 = new int[] { 98, 5, -1, 2, 3, 4 };
        Arrays.sort(arr3);
        System.out.println(Arrays.toString(arr3)); // [-1, 2, 3, 4, 5, 98]

        // 5. 二分查找
        int[] arr4 = new int[] { 1, 2, 3, 4, 5, 19, 29, 30, 100 };
        int index = Arrays.binarySearch(arr4, 4);
        int index2 = Arrays.binarySearch(arr4, 300);
        System.out.println(index); // 3
        System.out.println(index2); // -10(只要是负数，就是没找到，不一定是-10要看底层实现)

    }
}
