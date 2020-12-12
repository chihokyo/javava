import java.util.Arrays;
/**
 * 因为插入排序的特性 
 * 在有序数组的情况下，插入排序的时间复杂度是O(n) 而不是指数级 
 * 所以在有序数组的时候，插入排序的速度是快于选择排序的。
 * 下面做个测试。
 * 
 */
public class InsertionPKSelection {
    public static void main(String[] args) {
        int[] dataSize = { 10000, 100000 };
        for (int i : dataSize) {

            System.out.println("Random Array : ");
            Integer[] arr = ArrayGeneraor.generateRandomArray(i, i);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort2", arr);
            SortingHelper.sortTest("TestSelectionSort", arr2);
            System.err.println();

            System.out.println("Ordered Array : ");
            arr = ArrayGeneraor.generateOrderedArray(i);
            arr = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertionSort2", arr);
            SortingHelper.sortTest("TestSelectionSort", arr2);
            System.err.println();

        }
    }

    // 可以看出来，在有序的情况下，插入排序的速度不是一般的快！
    // Random Array :
    // InsertionSort2, n = 10000: 0.194515 s
    // TestSelectionSort, n = 10000: 0.170563 s

    // Ordered Array :
    // InsertionSort2, n = 10000: 0.001693 s
    // TestSelectionSort, n = 10000: 0.139437 s

    // Random Array :
    // InsertionSort2, n = 100000: 21.771151 s
    // TestSelectionSort, n = 100000: 13.104516 s

    // Ordered Array :
    // InsertionSort2, n = 100000: 0.009266 s
    // TestSelectionSort, n = 100000: 20.795007 s
}
