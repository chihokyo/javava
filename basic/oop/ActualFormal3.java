/**
 * 关于值传递 
 * 如果传递是引用数据类型，那么就是传递的就是一个地址，相同的地址复制了一份给形参。所以指向的是同一份数据。
 * 
 */
public class ActualFormal3 {
    public static void main(String[] args) {
        Data d1 = new Data();
        d1.m = 10;
        d1.n = 20;
        System.out.println("d1.m:" + d1.m + ", d1.n:" + d1.n); // d1.m:10, d1.n:20

        // int temp = d1.m;
        // d1.m = d1.n;
        // d1.n = temp;
        // System.out.println("d1.m:" + d1.m + ", d1.n:" + d1.n); // d1.m:20, d1.n:10

        // 为什么这里可以交换成功呢？
        // 上一个文件传入的是基本数据类型，其实是复制了一份新的数据
        // 而这一个虽然也是赋值了一份新的数据，而这时候data代表的是地址，也就是说此时复制的是一份同样的地址
        // 而这个地址指向的是同一份对象数据

        ActualFormal3 a = new ActualFormal3();
        a.swap(d1);
        System.out.println("d1.m:" + d1.m + ", d1.n:" + d1.n); // d1.m:20, d1.n:10
    }

    // 传入的是一个引用数据类型
    public void swap(Data data) {
        int temp = data.m;
        data.m = data.n;
        data.n = temp;
    }
}

class Data {

    int m;
    int n;
}