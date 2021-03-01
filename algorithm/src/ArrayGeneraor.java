import java.util.Random;

/**
 * 负责生成测试数组的类
 */
public class ArrayGeneraor {
    // 私有构造器 也不需要生成对象
    private ArrayGeneraor(){}
    /**
     * 生成有序数组
     * @param n 数组大小
     * @return
     */
    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
    /**
     * 生成一个长度n的随机数组，每个数字的范围是[0,bound)
     * @param n 数组大小
     * @param bound 随机数范围
     * @return
     */
    public static Integer[] generateRandomArray(int n, int bound) {
        Integer[] arr = new Integer[n];
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(bound);
        }
        return arr;
    }
}
