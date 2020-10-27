package com.interfacetest.exer;

public class ComparableCircleTest {
    public static void main(String[] args) {
        ComparableCircle c1 = new ComparableCircle(3.4);
        ComparableCircle c2 = new ComparableCircle(3.6);

        int compareValue = c1.compareTo(c2);
        if (compareValue > 0) {
            System.out.println("c1对象大");
        } else if (compareValue < 0) {
            System.out.println("c2对象大");
        } else {
            System.out.println("c1与c2一样大");
        }

        int compareValue1 = c1.compareTo(new String("AA"));
        System.out.println(compareValue1);
    }
}

/*
 * interface CompareObject{ public int compareTo(Object o); 
 * //若返回值是 0 , 代表相等;
 * 若为正数，代表当前对象大；负数代表当前对象小 }
 */
interface CompareObject {
    // 若返回值是 0 , 代表相等; 若为正数，代表当前对象大；负数代表当前对象小
    public int compareTo(Object o);
}

/**
 * 定义一个Circle类 声明radius属性 提供 get set方法
 */
class Circle {

    private Double radius;

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Circle() {
        super();
    }

    public Circle(Double radius) {
        super();
        this.radius = radius;
    }
}

/**
 * 定义一个ComparableCircle类，继承Circle类并且实现CompareObject接口。
 * 在ComparableCircle类中给出接口中方法compareTo的实现体，用来比较两个圆的半径大小。
 */
class ComparableCircle extends Circle implements CompareObject {

    public ComparableCircle(Double radius) {
        super(radius);
    }

    @Override
    public int compareTo(Object o) {
        // 判断是否同一引用
        if (this == o) {
            return 0;
        }

        // 判断半径
        if (o instanceof ComparableCircle) {
            // 必须把o强制转换成可以对比的对象 ComparableCircle
            ComparableCircle c = (ComparableCircle) o;
            // 错误的写法：强转精度损失
            // NG return (int)(this.getRadius() - c.getRadius();
            // 因为对象本身不可以比较大小
            // 但是 Double 实现了一个接口
            // 就可以使用包装类的方法 compareTo → Double的
            return this.getRadius().compareTo(c.getRadius());
        } else {
            return 0;
            // throw new RuntimeException("传入类型有问题");
        }
    }
}