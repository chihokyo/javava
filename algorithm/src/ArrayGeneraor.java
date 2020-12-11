/**
 * 负责生成测试数组的类
 */
public class ArrayGeneraor {
    // 私有构造器 也不需要生成对象
    private ArrayGeneraor(){}
    public static Integer[] generateOrderedArray(int n) {
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
}
