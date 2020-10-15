/**
 * sort排序算法
 * 
 */
public class SortArray {
    public static void main(String[] args) {
        int[] arr = new int[] { 18, 0, 5, 32, -2, -88, 1 };
        System.out.println("排序前");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        // 冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < (arr.length - i - 1); j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("排序后");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        int[] arr2 = new int[] { 18, 0, 5, 32, -2, -88, 1 };
        System.out.println("排序前");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
    }
}
