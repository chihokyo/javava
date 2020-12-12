/**
 * 一个工具类辅助数组
 */
public class SortingHelper {

    private SortingHelper() {
    }

    /**
     * 判断是否已排序
     * 
     * @param arr 数组
     */
    public static <E extends Comparable<E>> boolean isSorted(E[] arr) {

        // 记住从1开始，也就是第二个开始比较，第二个要一直比第一个大
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 测试输出排序详情
     * 
     * @param sortname 排序方法
     * @param arr      需要排序数组
     */
    public static <E extends Comparable<E>> void sortTest(String sortname, E[] arr) {

        long startTime = System.nanoTime();
        // 这里使用反射实现会更好
        if (sortname.equals("TestSelectionSort")) {
            TestSelectionSort.sort(arr);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1_000_000_000.0;

        if (!SortingHelper.isSorted(arr)) {
            throw new RuntimeException(sortname + "failed!!");
        }
        System.out.println(String.format("%s, n = %d: %f s", sortname, arr.length, time));

    }
}
