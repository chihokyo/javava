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

## final 关键字

| 修饰对象 |                      |                                             |
| -------- | -------------------- | ------------------------------------------- |
| Class    | 不能继承了【太监类】 | String System StringBuffer 都是不能被继承的 |
| method   | 不能重写了           | Object类中的getClass()                      |

关于变量的修饰 稍微麻烦点。

属性 就是Field 意思就是写在Class里的那个

### **有final的地方一定要初始化赋值！！**

```java
class Person {
  // 1 显示初始化 OK
  final int AGE = 5;
  // 2 默认初始化 NG 除非下面有构造器代码块初始化
  final int AGE;
  final String NAME; // 下面代码块赋值了
  final int ID;
  // 3 默认初始化
  {
    NAME = "Robot";
  }
  // 4 构造器 【如果多个重载构造器，每一个都要初始化赋值的 一个都不能少！】
  public Person(){
    ID = 99;
  }
  // 5 构造器 带参数
  public Person(int id){
    ID = id;
  }
}
```

### 方法里为什么不能初始化final变量？

常量的话，因为要在对象创造之前就要出生。但是方法是在对象之后出生的，不行。

### 有什么final赋值的小技巧吗？

如果大家拥有的都是一样的数据，简单的不知。**就显示初始化。**

如果赋值不是一个简简单单的值，而是一个方法 `ID=method()` 那么就建议在代码块里

如果大家拥有的不一样，就可以带参数的构造器，例如上面的5。

### final可以修饰参数吗？

```java
public void show(final int num){
  // 这里就是进去的常量
  // 不能进一步进行操作
  num += 10; // NG
}
```

### final可以修饰局部变量吗（方法内的变量）？

可以的，只能用，不能修改赋值啥的。和上面的形参一样。

```java
public void show(final int num){
  final int NUM = 10;
}
```

final （属性 方法）+ static（属性 方法 代码块 内部类） = 可以修饰属性or方法

修饰属性就是 === **全局常量**（接口常用）

修饰方法 == 不能重写 不能继承

### final经典题目

```java
public int addOne(final int x){
	return x++; // NG
	return ++x; // NG 
	return x + 1; // OK 没有改变x本身，只是加了
}
```

```java
public class Main {
    public static void main(String[] args) throws Exception {
        Other o = new Other();
        new Main().addOne(o);
    }
    public void addOne(final Other o) {
        // o = new Other(); // NG 
        o.i++; // o是final不能修改 i又不是 OK的
        System.out.println(o.i);
    }
}

class Other {
    public int i;
}
```
