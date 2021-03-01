# Javava

这个资源库主要记录了我初学*Java*的一个过程。因为属于是自学又没老师带，所以在搭建环境的时候感觉吃了不少苦头。

第一次学的时候虎头虎脑没有跟网上视频一样用Idea，而是用自己的vscode。所以在classpath的时候理解花了不少时间。

自己学习java目前大概是按照这样一个流程顺下来的。

- 基础语法
- 面向对象
- 异常
- 多线程
- 常用API
- 枚举
- 注解
- 集合
- IO流
- 泛型
- 反射
- JDBC

现在打算按照自己的理解，不定期的更新一下心得。（不按照顺序）


## Object类

无属性

1个空参构造器

一堆方法

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Base b = new Base();
        System.out.println(b.getClass()); // class Base
        System.out.println(b.getClass().getSuperclass()); // class java.lang.Object
    }
}
```

| Modifier and Type  | Method                                | Description                                                  |
| :----------------- | :------------------------------------ | :----------------------------------------------------------- |
| `protected Object` | `clone()`                             | Creates and returns a copy of this object.                   |
| `boolean`          | `equals(Object obj)`                  | Indicates whether some other object is "equal to" this one.  |
| `protected void`   | `finalize()`                          | **Deprecated.**The finalization mechanism is inherently problematic. |
| `Class<?>`         | `getClass()`                          | Returns the runtime class of this `Object`.                  |
| `int`              | `hashCode()`                          | Returns a hash code value for the object.                    |
| `void`             | `notify()`                            | Wakes up a single thread that is waiting on this object's monitor. |
| `void`             | `notifyAll()`                         | Wakes up all threads that are waiting on this object's monitor. |
| `String`           | `toString()`                          | Returns a string representation of the object.               |
| `void`             | `wait()`                              | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*. |
| `void`             | `wait(long timeoutMillis)`            | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |
| `void`             | `wait(long timeoutMillis, int nanos)` | Causes the current thread to wait until it is awakened, typically by being *notified* or *interrupted*, or until a certain amount of real time has elapsed. |

### == PK equals()

```java
public static void main(String[] args) throws Exception {
        int i = 10;
        int j = 10;
        double d = 10.0;
        char c = 10;
        System.out.println(i == j); // true 虽然有类型提升，但是比较的还是数值
        System.out.println(i == d); // true
        System.out.println(i == c); // true
        
        char c1 = 'A';
        char c2 = 65;
        System.out.println(c1 == c2); // true
}
```

| 数据类型     | ==       | equals()                                                     |
| ------------ | -------- | ------------------------------------------------------------ |
| 基本数据类型 | 比较数值 | 因为是方法，不能使用基本类型。【除非包装类】                 |
| 引用数据类型 | 比较地址 | 没重写。和== 一样。默认用父类【Object 比地址】<br />重写。看怎么重写的。基本上是内容。 |

### 如何重写equals()?

基本上用的方法都是自动生成。

先判断类型 后判断是否相等（这个相等基本数据类型用== 引用数据类型`equals()`）

```java
public boolean equals(Object obj) {
    // 地址都一样了，那肯定一样。
		if(this == obj) {
      return true;
    }
   // 判断类型
  if(obj instanceof Order ){
    Order order = (Order)obj;
    // OK
    return this.orderId == order.orderId && this.orderName.equals(order.orderName);
    // NG
    return this.orderId == order.orderId && this.orderName == order.orderName;
  }
}
```

### `toString()`是干嘛的？

-  **Object 类的 toString 方法返回一个字符串，该字符串由类名（对象是该类的一个实例）、at 标记符“@”和此对象哈希码的无符号十六进制表示组成**。

  源码 

  ```java
  
  public String toString() {
          return getClass().getName() + "@" + Integer.toHexString(hashCode());
  }
  ```

  - 在String与其他数据类型进行连接的时候自动调用`toString()`方法
  - 重写`toString()`方法比较好
  - 基本类型数据转换为String类型的时候，调用了包装类的`toString()`方法

```java
Date now = new Date();
        System.out.println("Now: " + now);
        System.out.println("Now: " + now.toString()); // 上下一样
        
        String s1 = "Hello";
        System.out.println(s1); // Hello
        
        int i1 = 199;
        System.out.println(i1); // 199
}
```

### 如何重写toString？

虽然系统能自动给。基本上就是我这样的写法。

```java
public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", balance='" + getBalance() + "'" +
            ", annualInterestRate='" + getAnnualInterestRate() + "'" +
            "}";
}
```
