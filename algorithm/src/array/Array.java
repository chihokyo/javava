package array;

/**
 * 关于数组
 * 
 */
public class Array {
    public static void main(String[] args) {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] scores = new int[] { 100, 99, 90 };
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }
        // 这里是体验可迭代性。复制了一份。
        for (int i : scores) {
            System.out.println(i);
        }

        scores[0] = 11; // 下标修改
        for (int i : scores) {
            System.out.println(i);
        }
    }

}
