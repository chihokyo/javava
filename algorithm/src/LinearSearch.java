/**
 * 线性查找法 
 * 找到返回index 
 * 找不到返回 -1
 */
public class LinearSearch {

    // 如果不想让这个类可以实例化怎么办。
    // 使用构造函数私有化
    // 但是这样不能阻止本类来实例化。
    private LinearSearch (){};

    public static int search(int[] data, int target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    // 如果for和if这样只有一句statement 就是可以省略大括号的
    // 顺便java是可以不缩进的，但是为了层次可读性。还是加上了。
    public static int search2(int[] data, int target) {
        for (int i = 0; i < data.length; i++)
            if (data[i] == target)
                return i;
        return -1;
    }

    public static void main(String[] args) {

        int[] data = { 1, 29, -9, 18, 7 };
        // 测试1
        // LinearSearch ls = new LinearSearch();
        // int res = ls.search(data, 18);
        // int res2 = ls.search(data, 0);
        // System.out.println(res);
        // System.out.println(res2);

        // 测试2
        // 无需新建一个对象，直接static调用静态方法。多用于工具类。
        int res = LinearSearch.search(data, 1);
        int res2 = LinearSearch.search(data, 20);
        System.out.println(res);
        System.out.println(res2);


    }
}
