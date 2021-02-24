/**
 * 测试选择排序类
 */
public class TestSelectionSort {

    private TestSelectionSort() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minIndex]) <= 0)
                    minIndex = j;
            }
            swap(arr, i, minIndex);
        }
    }

    public static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        int[] dataSize = { 10000, 100000 };
        for (int i : dataSize) {
            Integer[] arr = ArrayGeneraor.generateRandomArray(i, i);
            SortingHelper.sortTest("TestSelectionSort", arr);
        }
    }

    // TestSelectionSort, n = 10000: 0.133216 s
    // TestSelectionSort, n = 100000: 12.353799 s
    // 可以看出来，在数据量10倍的情况下，速度大概慢了100倍，也就是说时间复杂度大概是指数级，O(n^2)
}
