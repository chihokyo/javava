/**
 * 插入排序
 * 
 */
public class InsertionSort {

    private InsertionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //    for (int j = i; j - 1 >= 0; j--) {
            //        // 如果后一个比前一个还要小
            //        if (arr[j].compareTo(arr[j -1]) < 0) {
            //            swap(arr, j - 1, j);
            //        } else {
            //            break;
            //        }
            //    }
            
            // 可以看出来在for里面就一个if进行判断，那么可以直接缩写成这样
            for (int j = i; j - 1 >= 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] dataSize = { 10000, 100000 };
        for (int i : dataSize) {
            Integer[] arr = ArrayGeneraor.generateRandomArray(i, i);
            SortingHelper.sortTest("InsertionSort", arr);
        }
    }
}
