/**
 * 1 可变个数形参的内容 
 * 2 具体使用 
 *      2.1 可变个数形参的格式 数据类型 变量名 
 *      2.2 可变个数形参在一个方法中最多只能有1个，并且只能放在末尾
 */

public class ValueArgs {
    public static void main(String[] args) {
        ValueArgs v1 = new ValueArgs();
        v1.show(12);
        v1.show("hello");
        // v1.show("hello", "world");
        // v1.show();
        // v1.show("hello", "world", 3); // NG
        // 如果使用历史遗留形式进行数组调用的话，需要下面的形式
        // v1.show(new String[]{"hello1","wolrd2"});
        // 而使用可变形参无需新建一个数组
        v1.show("hello3", "wolrd4");

    }

    public void show(int i) {
        System.out.println("int i");
    }

    //
    public void show(String s) {
        System.out.println("String s");

    }

    // 历史遗留要素 约等于下面 可变形参 的形式
    // public void show(String[] strs) {
    // System.out.println("String[] strs");

    // }

    // 可变形参进行遍历
    public void show(String... strs) {
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }

    }

    // public void show(String[] strs, int i) 这样放在前面是不行的
    public void show(int i, String[] strs) {
        System.out.println("int i, String[] strs");
    }
}
