/**
 * 关于值传递 
 * 如果参数是基本数据类型，此时实参赋值给形参的是，是实参真实存储的数据值。2个都是独立的。
 */

public class ActualFormal2 {
    public static void main(String[] args) {
        System.out.println("************基本数据类型***************");

        int m = 10;
        int n = 20;
        System.out.println("m:" + m + ", n:" + n); // m:10, n:20

        // int temp = m;
        // m = n;
        // n = temp;
        // System.out.println("m:" + m + ", n:" + n); // m:20, n:10

        ActualFormal2 a1 = new ActualFormal2();
        a1.swap(m, n); // 并没有换成，为什么没有换成呢
        // 因为传入到swap方法中的形参，交换的也是swap形参的m和n，而不是原来的m和n
        // 那么如何解决呢

        System.out.println("m:" + m + ", n:" + n); // m:10, n:20

    }

    // 交换变量方法
    // （其实这里的形参定义成i，j也行）
    public void swap(int m, int n) {
        int temp = m;
        m = n;
        n = temp;
    }
}
