/**
 * 排序算法-选择排序
 */

public class SelectionSort {

    /**
     * 私有化构造器，无法new
     */
    private SelectionSort() {
    }

    /**
     * 排序算法
     */
    public static void sort(int[] arr) {
        // 循环不变量 （每一次循环之后这个就是不变的）
        // arr[0...i) 是有序的
        // arr[i...n) 是无序的
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 貌似这里 j = i 和 j = i + 1 都可以
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 交换方法 为什么是静态，因为前面的是静态。
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 测试开始
     */
    public static void main(String[] args) {
        int[] arr = { 24, 18, 12, 9, -6, 89 };
        SelectionSort.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
