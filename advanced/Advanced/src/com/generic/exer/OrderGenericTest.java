package com.generic.exer;
/**
 * 自定义泛型类
 */
public class OrderGenericTest {
    
    public static void main(String[] args) {
        OrderGeneric<Object> orderG = new OrderGeneric<Object>();
        orderG.setOrderT(123);
        // 如果定义了泛型类，实例化没有指名类型 默认就是Object类型
        // 但其实现在编译器都必须要指定了，所以我也就是指定成了Object类型
        // 但是不建议
        orderG.setOrderT("AB");
        // 新建一个String类型
        OrderGeneric<String> orderG2 = new OrderGeneric<String>("yes", 15, "AA");
        System.out.println(orderG2);
        orderG2.setOrderT("BB");
        System.out.println(orderG2);
        // { orderName='yes', orderId='15', orderT='AA'}
        // { orderName='yes', orderId='15', orderT='BB'}

        // 情况1示例
        SubOrderGeneric sog = new SubOrderGeneric();
        sog.setOrderT(123);
        System.out.println(sog); // { orderName='null', orderId='0', orderT='123'}

        // 情况2示例
        // SubOrderGeneric2<String> sog2 = new SubOrderGeneric2<String>();
        // 类推断
        SubOrderGeneric2<String> sog2 = new SubOrderGeneric2<>();
        sog2.setOrderT("YYY");
        System.out.println(sog2); // { orderName='null', orderId='0', orderT='YYY'}
    }
}


class OrderGeneric<T> {
    String orderName;
    int orderId;

    // 想象这是一个T类 但是其实没实例化之前是无法确定T是什么类型的
    T orderT;

    public OrderGeneric() {}

    public OrderGeneric(String orderName, int orderId, T orderT) {
        this.orderName = orderName;
        this.orderId = orderId;
        this.orderT = orderT;
    }

    public T getOrderT() {
        return orderT;
    }

    public void setOrderT(T orderT) {
        this.orderT = orderT;
    }

    @Override
    public String toString() {
        return "{" +
            " orderName='" + orderName + "'" +
            ", orderId='" + orderId + "'" +
            ", orderT='" + getOrderT() + "'" +
            "}";
    }
    
}

// 情况1 如果有了继承类 父类指名泛型
// 由于子类在继承带泛型的父类时，父类已经指名了泛型，那么子类就无需指名。
// 子类就只是一个普通类

class SubOrderGeneric extends OrderGeneric<Integer> {
    // 上面
}

// 情况2 继承类没有指名
// 子类就是一个泛型类了

class SubOrderGeneric2<T> extends OrderGeneric<T> {
    // 上面
}