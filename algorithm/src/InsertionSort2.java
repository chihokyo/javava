import java.util.Arrays;

/**
 * 插入排序特性-使用赋值
 */
public class InsertionSort2 {

    private InsertionSort2() {
    }

    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // arr[i] 插入到合适的位置
            E t = arr[i]; // 这里是待定区，就是拿出来需要赋值的数字
            int j; // 需要被赋值的数组的所以位置
            for (j = i; j - 1 >= 0 && t.compareTo(arr[j - 1]) < 0; j--) {
                // 小的到前面
                arr[j] = arr[j - 1];
            }
            arr[j] = t;
        }
    }
    
    public static void main(String[] args) {
        int[] dataSize = {10000, 100000};
        for (int i : dataSize) {
            Integer[] arr = ArrayGeneraor.generateRandomArray(i, i);
            // 拷贝一份用来和 InsertionSort 作对比
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            // 速度对比（其实差距并不会特别大但交换操作步骤是3步还是会费时，复杂度是相同的指数级O()n^2）
            SortingHelper.sortTest("InsertionSort", arr);
            SortingHelper.sortTest("InsertionSort2", arr2);
        }
    }
}
