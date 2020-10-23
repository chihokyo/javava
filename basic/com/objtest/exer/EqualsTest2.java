package com.objtest.exer;
/**
 * 关于 重写equals() == 的练习题1
 */
public class EqualsTest2 {
    public static void main(String[] args) {

        OrderTest order1 = new OrderTest(1001, "orderName1");
        OrderTest order2 = new OrderTest(1002, "orderName2");
        OrderTest order3 = new OrderTest(1002, "orderName2");
        System.out.println(order1 == order2); // false
        System.out.println(order1.equals(order2)); // false

        System.out.println(order2 == order3); // false
        System.out.println(order2.equals(order3)); // true

        // 关于String的特殊问题
        String s1 = "AAA";
        String s2 = "AAA";
        // 因为String用的是常量池 所以这里内存会认为是 true
        System.out.println(s1 == s2); // true
    }
}

class OrderTest {

    private int orderId;
    private String orderName;

    public int getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public OrderTest(int orderId, String orderName) {
        super();
        this.orderId = orderId;
        this.orderName = orderName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof OrderTest) {
            OrderTest order = (OrderTest) obj;
            // OK 引用数据一定要用equals
            return this.orderId == order.orderId && this.orderName.equals(order.orderName);
            // NG
            // return this.orderId == order.orderId && this.orderName == order.orderId);
        }

        return false;
    }
}
