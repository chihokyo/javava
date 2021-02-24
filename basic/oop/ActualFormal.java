/**
 * 关于实参和形参
 * 
 * 基本数据类型 int float。。。 赋值的是变量保存的数据值 
 * 引用属性类型 此时赋值的是变量所保存的数据的地址（这里是一个地址，而不是一个真实的对象）
 */

public class ActualFormal {
    public static void main(String[] args) {
        System.out.println("************基本数据类型***************");
        // 基本数据类型赋值都一样
        // m赋值给了n 其实10在内存里有2块
        int m = 10;
        int n = m;
        System.out.println("m:" + m + ", n:" + n); // m:10, n:10
        n = 20;
        System.out.println("m:" + m + ", n:" + n); // m:10, n:20

        System.out.println("************引用数据类型***************");

        Order o1 = new Order();
        o1.orderId = 1001;
        // 引用类型遍历赋值 就是把o1地址给了o2
        // 都指向了同一个堆heap对象实体
        Order o2 = o1;
        System.out.println("o1.orderId:" + o1.orderId + ", o2.orderId:" + o2.orderId); // o1.orderId:1001,
                                                                                       // o2.orderId:1001

        o2.orderId = 1002;
        System.out.println("o1.orderId:" + o1.orderId + ", o2.orderId:" + o2.orderId); // o1.orderId:1002,
                                                                                       // o2.orderId:1002

    }
}

class Order {

    int orderId;
}