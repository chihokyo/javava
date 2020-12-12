/**
 * 排序算法-选择排序 使用泛型进行
 */

public class SelectionSort2 {

    /**
     * 私有化构造器，无法new
     */
    private SelectionSort2() {
    }

    /**
     * 排序算法
     */
    public static <E extends Comparable<E>> void sort(E[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            // 貌似这里 j = i 和 j = i + 1 都可以
            for (int j = i + 1; j < arr.length; j++) {
                // 这里就需要使用接口函数 compareTo
                if (arr[j].compareTo(arr[minIndex]) <= 0) {
                    minIndex = j;
                }
            }
            swap(arr, i, minIndex);
        }
    }

    /**
     * 交换方法 为什么是静态，因为前面的是静态。
     */
    private static <E> void swap(E[] arr, int i, int j) {
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 测试开始
     */
    public static void main(String[] args) {
        // int[] arr = { 24, 18, 12, 9, -6, 89 };
        // 因为使用了泛型，这里就不能是int了，而是一个可以比较的类
        Integer[] arr = { 1, 2, -9, -6, 48, 3 };
        SelectionSort2.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        /********************************************* */

        Student2[] stu2s = { new Student2("Alice", 100), new Student2("Chin", 80), new Student2("Tom", 18),
                new Student2("Bob", 55), };

        SelectionSort2.sort(stu2s);
        for (Student2 student2 : stu2s) {
            // 本身是对象打印不出来的，重写了toString
            System.out.print(student2 + " ");
        }
        System.out.println();
    }
}

/**
 * 自动以一个可以比较的类
 */

class Student2 implements Comparable<Student2> {

    private String name;
    private int score;

    public Student2(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    /**
     * 实现 Comparable 接口都需要重写 compareTo
     */
    @Override
    public int compareTo(Student2 another) {
        // if(this.score < another.score)
        //      return -1;
        // else if (this.score == another.score)
        //      return 0;
        //      return 1;
        // 以上简写成这样
        
        // 从小到大
        // return this.score - another.score;
        // 从大到小
        return another.score - this.score;
    }

    @Override
    public String toString() {
        return String.format("Student2(name: %s, score %d)", name, score);
    }

}