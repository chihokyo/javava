# 设计模式(Desing Patterns)

## 写在前面的

设计模式感觉蛮有用的，具体代码写逻辑的时候感觉算法有用。在实际上写一个项目的时候感觉设计模式有用。

## 1. 七大原则

### 单一职责原则(Single Responsibility Principle-SRP)

简单的就是 **一个类一个职责**，一个职责并不代表就写一个方法一个代表，偏抽象的职责。

单一不仅仅是类还有可能是方法。反正模块化也不是*Java*独有的，其他编程都一样。

至于这个原则的好处还用问吗，问就是<u>低耦合高内聚</u>！

**进化前**

根本不是单一职责

```java
package com.design.princple;

/**
 * 单一职责 方案A
 */
public class SingleResponsibilityA {
    public static void main(String[] args) {
        Vehicle v = new Vehicle();
        // 这样不行的，这么多交通工具是要拆分的
        // 毕竟单一职责，一个方法怎么可以干这么多
        v.run("Plane");
        v.run("Tesla");
        v.run("Train");
    }
}

/**
 * 假设写一个交通工具类 有飞机，火车，汽车，走路
 * 下面就不行了，因为你发现只能在公路跑
 */
class Vehicle {
    public void run(String vehicle){
        System.out.println(vehicle + " Road run....");
    }
}

```

**二级进化**

实现类级别的分解

```java
package com.design.princple;

/**
 * 单一职责 方案B
 */
public class SingleResponsibilityB {
    public static void main(String[] args) {

        RoadVehicle rv = new RoadVehicle();
        AirVehicle av = new AirVehicle();
        SeaVehicle sv = new SeaVehicle();
        rv.run("Gaotie");
        av.run("ANA");
        sv.run("Hexie");
    }
}

/**
 * 遵守了单一职责原则 
 * 该谁的活儿哪个类就去干 → 分解类
 */
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " RoadVehicle run....");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " AirVehicle run....");
    }
}

class SeaVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " SeaVehicle run....");
    }
}

```

**三级进化**

实现方法级别的分解

```java
package com.design.princple;

/**
 * 单一职责 方案C
 */
public class SingleResponsibilityC {
    public static void main(String[] args) {

        RoadVehicle rv = new RoadVehicle();
        AirVehicle av = new AirVehicle();
        SeaVehicle sv = new SeaVehicle();
        rv.run("Gaotie");
        av.run("ANA");
        sv.run("Hexie");
    }
}

/**
 * 遵守了单一职责原则 
 * 比起 SingleResponsibilityB 并没有在类上面遵循单一职责
 * 但是在方法的级别上还是遵循了单一职责
 */
class Vehicle2 {

    public void run(String vehicle){
        System.out.println(vehicle + " Road run....");
    }
    public void runAir(String vehicle){
        System.out.println(vehicle + " Air run....");
    }
    public void runSea(String vehicle){
        System.out.println(vehicle + " Sea run....");
    }
}
```

### 接口隔离原则（Interface Segregation Principle）

客户端不应该依赖不需要的接口，即一个类对另一个类的依赖应该在最小的接口上。

本质上就是说，如果一个接口有10个方法，但是你只用的上其中的5个。那么不是应该全部实现10个。而是应该去分解这个10个接口。

**进化前**

```java
interface SegregationA1 {
    void seA1();
    void seA2();
    void seA3();
    void seA4();
    void seA5();
}

class SegregationAB implements SegregationA1 {

    @Override
    public void seA1() {
       System.out.println("SegregationAB seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAB seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAB seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAB seA5");
    }
}

class SegregationAC implements SegregationA1 {

    @Override
    public void seA1() {
       System.out.println("SegregationAD seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationAD seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationAD seA3");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationAD seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationAD seA5");
    }
}

// SegregationAD 通过接口 SegregationA1 去（依赖）使用 SegregationAB
// 但是只会用A1 A2 A3
// 这样的结果就是其实 SegregationAB 的 4和5白写了
class SegregationAD {
    public void depend1(SegregationA1 i) {
        i.seA1();
    }
    public void depend2(SegregationA1 i) {
        i.seA2();
    }
    public void depend3(SegregationA1 i) {
        i.seA3();
    }
}

// SegregationAE 通过接口 SegregationA1 去（依赖）使用 SegregationAC
// 但是只会用A4 A5
// 这样的结果就是其实 SegregationAC 的 2，3白写了
class SegregationAE {
   	public void depend1(SegregationA1 i) {
        i.seA1();
    }
    public void depend4(SegregationA1 i) {
        i.seA4();
    }
    public void depend5(SegregationA1 i) {
        i.seA5();
    }
}
```

**进化后**

实际上就是把上面的*SegregationA1*拆分成3份 需要什么实现什么 然后通过接口进行连接起来

```java
public class InterfaceSegregationB {
    public static void main(String[] args) {

        BBA bba = new BBA();
        // BBA 通过 接口 去依赖 BA
        bba.depend1(new SegregationBA()); // SegregationBA seA1
        bba.depend2(new SegregationBA()); // SegregationBA seA2
        bba.depend3(new SegregationBA()); // SegregationBA seA3

        BBB bbb = new BBB();
        // BBB 通过 接口 去依赖 BB
        bbb.depend1(new SegregationBB()); // SegregationBB seA1
        bbb.depend4(new SegregationBB()); // SegregationBB seA4
        bbb.depend5(new SegregationBB()); // SegregationBB seA5
    }
}

// 根据具体需求把上面进化前的接口拆分成了3份
interface SegregationB1 {
    void seA1();
}

interface SegregationB2 {
    void seA2();

    void seA3();
}

interface SegregationB3 {
    void seA4();

    void seA5();
}

// 分别进行实现 需要什么实现什么
class SegregationBA implements SegregationB1, SegregationB2 {

    @Override
    public void seA1() {
        System.out.println("SegregationBA seA1");
    }

    @Override
    public void seA2() {
        System.out.println("SegregationBA seA2");
    }

    @Override
    public void seA3() {
        System.out.println("SegregationBA seA3");
    }

}

// 分别进行实现 需要什么实现什么
class SegregationBB implements SegregationB1, SegregationB3 {

    @Override
    public void seA1() {
        System.out.println("SegregationBB seA1");
    }

    @Override
    public void seA4() {
        System.out.println("SegregationBB seA4");
    }

    @Override
    public void seA5() {
        System.out.println("SegregationBB seA5");
    }

}

class BBA {
    // 这里就是放进去需要实现的接口
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend2(SegregationB2 i) {
        i.seA2();
    }

    public void depend3(SegregationB2 i) {
        i.seA3();
    }
}

class BBB {
    public void depend1(SegregationB1 i) {
        i.seA1();
    }

    public void depend4(SegregationB3 i) {
        i.seA4();
    }

    public void depend5(SegregationB3 i) {
        i.seA5();
    }
}
```

### 依赖倒转原则（Dependence Inversion Principle）

- 高层模块不应该依赖底层模块，相反，底层模块应该依赖抽象高层模块
- 抽象不应该依赖细节。
- 中心思想，面向接口编程。
- 其实本质就是抽象思想。。各种具体的全部抽象起来，全部规范起来。用接口去完成。

现在写一个例子看一下这个原则

写一个Person接收信息的例子

**进化前**

```java
public class DependenceInversionA {
    public static void main(String[] args) {
        PersonA p = new PersonA();
        p.recieve(new EmailA()); // Email: Hello World
    }
}

class EmailA {
    public String getInfo() {
        return "Email: Hello World";
    }
}

// 方式1 分析
// 1 简单
// 2 但是如果这个时候接收的只是Email 有可能是微信 短信等等呢？
// 这样就会无限增加类 Person也要增加方法
// 3 解决思路：引入一个抽象接口，IReceiver 表示接收者
// Person 和 IReceiver 就是在一起有依赖
class PersonA {
    public void recieve(EmailA email) {
        System.out.println(email.getInfo());
    }
}
```

**进化后**

```java
public class DependenceInversionB {
    public static void main(String[] args) {
        PersonB p = new PersonB();
        p.receive(new EmailB()); // Email....
        p.receive(new Wechat()); // Wechat....
    }
}

interface IReceiver {
    public String getInfo();
}

// 这个方式是EmailB
class EmailB implements IReceiver {
    @Override
    public String getInfo() {
        return "Email....";
    }
}

// 这个方式如果是Wechat
class Wechat implements IReceiver {
    @Override
    public String getInfo() {
        return "Wechat....";
    }
}

class PersonB {
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}
```

依赖关系传递的3种方式

- 接口传递
- 构造方法传递
- setter方式传递

```java
public class DependenceInversionC {
    public static void main(String[] args) {
        // // A 通过接口
        // OpenClose oc = new OpenClose();
        // oc.open(new Sony()); // Sony open

        // // B 通过构造方法
        // Sony s = new Sony();
        // OpenClose oc = new OpenClose(s);
        // oc.open(); // Sony open

        // C 通过setter方法传递
        OpenClose oc = new OpenClose();
        oc.setTV(new Sony());
        oc.open(); // Sony open


    }
}

// // A 通过接口
// interface SwitchOpenClose {
//     void open(TV tv);
// }

// interface TV {
//     void play();
// }

// // 实现接口
// class OpenClose implements SwitchOpenClose {
//     @Override
//     public void open(TV tv) {
//         tv.play();
//     }
// }

// SONY实现
class Sony implements TV {
    @Override
    public void play() {
        System.out.println("Sony open");
    }
}

// // B 通过构造方法
// interface SwitchOpenClose {
//     void open();
// }

// interface TV {
//     void play();
// }

// class OpenClose implements SwitchOpenClose {
//     public TV tv;
//     // 这里通过构造器，这里需要传入一个实现类
//     public OpenClose(TV tv) {
//         this.tv = tv;
//     }
//     @Override
//     public void open() {
//         this.tv.play();
//     }
// }

// C 通过setter方法传递
interface SwitchOpenClose {
    void open();
    // 抽象方法，这里必须要实现，就会通过set方法进行初始化了
    void setTV(TV tv);
}

interface TV {
    void play();
}

class OpenClose implements SwitchOpenClose {
    // 这里是私有的
    private TV tv;
    public void setTV(TV tv) {
        this.tv = tv;
    }
    @Override
    public void open() {
        this.tv.play();
    }
}
```

### 里氏替换原则（Liskov Substitution）

这个主要是对**继承**的一次大考验。主要就是说，如果子类继承了父类的方法

- 全部重写 → 那干嘛继承
- 重写太多 → 那样父类如果变化了 子类怎么办 重写的越多耦合性多大
- **结论就是尽量不要重写父类的方法** 如果要改 就需要组合，聚合这样

A类有1234方法 B类重写123方法 按照这个理论的话 那么建议A类和B类弄一个上层的更加BASE类。A和B之间的基础方法都给这个BASE类。

那样就可以解耦合。聚合，组合，依赖这样来解决问题。

**进化前**

```java
public class LiskovSubstitution {
    public static void main(String[] args) {

        LiskovA a = new LiskovA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        LiskovB b = new LiskovB();
        // 这里的b是重写了func1 但是很容易就会忘记你已经重写了
        System.out.println("11 - 3 = " + b.func1(11, 3));
        System.out.println("1 - 8 = " + b.func1(1, 8));
        System.out.println("11 + 3 + 9 = " + b.func2(11, 3));

    }
}

/**
 * A类
 */
class LiskovA {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * B类
 * 增加了一个新功能 在ab相加的基础上+9
 */
class LiskovB extends LiskovA {
    // 这里可以无意识重写了
    // 这时候就不满足里氏替换原则
    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }
}
```

**进化后**

A和B 这里基本解耦 

```java
public class LiskovSubstitutionB {
    public static void main(String[] args) {
        LiskovAA a = new LiskovAA();
        System.out.println("11 - 3 = " + a.func1(11, 3));
        System.out.println("1 - 8 = " + a.func1(1, 8));

        System.out.println("-------------------");
        LiskovBB b = new LiskovBB();
        // 因为BB不在继承AA类 所以调用不会再认为func1是减法了
        // 调用的功能就会明确
        System.out.println("11 + 3 = " + b.func1(11, 3));
        System.out.println("1 + 8 = " + b.func1(1, 8));

        // 使用组合依然可以使用到AA的相关方法
        // 这里的func3 其实调用的是AA类的
        System.out.println("11 - 3" + b.func3(11, 3));

    }
}


class LiskovBase {
}

/**
 * AA类
 */
class LiskovAA extends LiskovBase {
    public int func1(int num1, int num2) {
        return num1 - num2;
    }
}

/**
 * BB类
 */
class LiskovBB extends LiskovBase {
    // 如果这个时候BB要使用AA的func1
    private LiskovAA a = new LiskovAA();

    public int func1(int a, int b) {
        return a + b;
    }

    public int func2(int a, int b) {
        return func1(a, b) + 9;
    }

    public int func3(int a, int b) {
        return this.a.func1(a, b);
    }
}
```

### 开闭原则（Open Closed Principle）

这个很容易理解，因为用的多了。就是开，对外开放。闭，对内闭上。说这么多，其实就像获取的方法，谁都能获取。但是修改我的方法，缺不行。就是拓展可以扩展，但不能修改自身。

- 最基础，最重要。
- 一个软件实体如类，对函数和模块应该是扩展开放（对提供方），对修改关闭（对使用方）。用抽象构建框架，实现扩展细节。
- 软件发生变化的时候不应该修改框架。
- 编程中遵循其他原则，使用模式的目的就是遵循开闭原则

**进化前**

这里实现一个画各种图形的类。

这里每次新增一个图形就要赠一个①1个if句②画图类的方法③一个新的图类

优点 好理解，操作

缺点 违反了ocp原则

```java
public class OpenClosedA {
    public static void main(String[] args) {
        GraphicEditorA g = new GraphicEditorA();
        g.drawShape(new RectangleA());
        g.drawShape(new CircleA());
        g.drawShape(new TriangleA());
    }
}

class GraphicEditorA {
    /**
     * 根据不同Shape对象 根据type 绘制不同图形
     */
    public void drawShape(ShapeA s) {
        if (s.m_type == 1) {
            drawRectangle(s);
        } else if (s.m_type == 2) {
            drawCircle(s);
        } else if (s.m_type == 3){
            drawTriangle(s);
        }
    }

    public void drawRectangle(ShapeA r) {
        System.out.println("绘制矩形");
    }
    public void drawCircle(ShapeA c) {
        System.out.println("绘制圆型");
    }
    public void drawTriangle(ShapeA t) {
        System.out.println("绘制三角形");
    }
}

class ShapeA {
    int m_type;
}
/**
 * 矩形
 */
class RectangleA extends ShapeA {
    RectangleA(){
        super.m_type = 1;
    }
}

/**
 * 原型
 */
class CircleA extends ShapeA {
    CircleA(){
        super.m_type = 2;
    }
}

/**
 * 三角形【新增的话】
 */
class TriangleA extends ShapeA {
    TriangleA(){
        super.m_type = 3;
    }
}
```

**进化后**

改进思路，把shape做成抽象类。提供一个*draw抽象方法*让子类分别去实现。这样直接利用多态就可以了。

```java
public class OpenClosedB {
    public static void main(String[] args) {

        GraphicEditorB g = new GraphicEditorB();
        g.drawShape(new RectangleB());
        g.drawShape(new CircleB());
        g.drawShape(new TriangleB());
        g.drawShape(new OtherGraphic());
    }
}

class GraphicEditorB {

    public void drawShape(ShapeB s) {
        s.draw();
    }
}

abstract class ShapeB {
    int m_type;

    public abstract void draw();
}

class RectangleB extends ShapeB {
    RectangleB() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形");
    }
}

class CircleB extends ShapeB {
    CircleB() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形");
    }
}

class TriangleB extends ShapeB {
    TriangleB() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制三角形");
    }
}

// 这个时候如果新增其他图像
class OtherGraphic extends ShapeB {
    OtherGraphic() {
        super.m_type = 4;
    }

    @Override
    public void draw() {
        System.out.println("Other");
    }
}
```

### 迪米特法则（Demeter Principle）

又叫最少原则，就是一个类对自己依赖的类知道的越少越好。

这里我的理解，就是你的类的增删改查等等方法，不要把逻辑写在别的类下面。

> 直接的朋友:**每个对象都会与其他对象有耦合关系**，只要两个对象之间有耦合关系，我们就说这两个对象之间 是朋友关系。耦合的方式很多，依赖，关联，组合，聚合等。其中，我们称出现**成员变量**，**方法参数**，**方法返回值**中的类为**直接的朋友**，而出现在**局部变量**中的类<u>不是直接的朋友</u>。也就是说，陌生的类最好不要以局部变量的形式出现在类的内部。



这里有一个例子说了一下有什么区别

有一个学校，下属有各个学院和总部，现在要求打印学校总部员工ID和学院员工ID。

```java
public class DemeterA {
    public static void main(String[] args) {
        SchoolManagerA s = new SchoolManagerA();
        s.printAll(new CollegeManagerA());
    }
}

/**
 * 总部员工类
 */
class EmployeeA {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

/**
 * 学院员工类
 */

class CollegeEmployeeA {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**
 * 学院员工类-管理类
 */
class CollegeManagerA {

    public List<CollegeEmployeeA> getAllEmploye() {
        List<CollegeEmployeeA> list = new ArrayList<CollegeEmployeeA>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployeeA emp = new CollegeEmployeeA();
            emp.setId("CollegeEmployee id = " + i);
            list.add(emp);
        }
        return list;
    }
}

/**
 * 总部员工类-管理类
 */
class SchoolManagerA {

    public List<EmployeeA> getAllEmploye() {
        List<EmployeeA> list = new ArrayList<EmployeeA>();
        for (int i = 0; i < 5; i++) {
            EmployeeA emp = new EmployeeA();
            emp.setId("EmployeeA id = " + i);
            list.add(emp);
        }
        return list;
    }

    /**
     * 获取上面2者全部 
     * 这里的 CollegeEmployee 不是 SchoolManager 的直接朋友
     * CollegeEmployee 是以局部变量方式出现在 SchoolManager
     * 违反了 迪米特法则
     */
    void printAll(CollegeManagerA sub) {

        List<CollegeEmployeeA> list1 = sub.getAllEmploye();
        System.out.println("-------CollegeEmployeeA-------");
        for (CollegeEmployeeA collegeEmployeeA : list1) {
            System.out.println(collegeEmployeeA.getId());
        }

        List<EmployeeA> list2 = this.getAllEmploye();
        System.out.println("-------EmployeeA-------");
        for (EmployeeA employeeA : list2) {
            System.out.println(employeeA.getId());
        }
    }
}
```

**进化后**

其实这个法则这样看，一般人都不会写那种进化前的。

```java
public class DemeterB {
    public static void main(String[] args) {
        SchoolManagerB s = new SchoolManagerB();
        s.printAll(new CollegeManagerB());
    }
}


/**
 * 总部员工类
 */
class EmployeeB {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

/**
 * 学院员工类
 */

class CollegeEmployeeB {
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

/**
 * 学院员工类-管理类
 */
class CollegeManagerB {

    // 新建10个学院员工
    public List<CollegeEmployeeB> getAllCollegeEmploye() {
        List<CollegeEmployeeB> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CollegeEmployeeB emp = new CollegeEmployeeB();
            emp.setId("CollegeEmployeeB id = " + i);
            list.add(emp);
        }
        return list;
    }
    // 打印输出
    public void printCollegeEmploye() {
        List<CollegeEmployeeB> list = getAllCollegeEmploye();
        System.out.println("-------CollegeEmployeeB-------");
        for (CollegeEmployeeB collegeEmployeeB : list) {
            System.out.println(collegeEmployeeB.getId());
        }
    }

}

/**
 * 总部员工类-管理类
 */
class SchoolManagerB {

     // 新建5个总部学院员工
    public List<EmployeeB> getAllEmploye() {
        List<EmployeeB> list = new ArrayList<EmployeeB>();
        for (int i = 0; i < 5; i++) {
            EmployeeB emp = new EmployeeB();
            emp.setId("EmployeeB id = " + i);
            list.add(emp);
        }
        return list;
    }

    /**
     * 获取上面2者全部 
     * 这里的 CollegeEmployee 不是 SchoolManager 的直接朋友
     * CollegeEmployee 是以局部变量方式出现在 SchoolManager
     * 违反了 迪米特法则
     */
    void printAll(CollegeManagerB sub) {
        // 分析问题 
        // 输出学院的员工方法 封装到 CollegeManagerB
        sub.printCollegeEmploye();

        List<EmployeeB> list2 = this.getAllEmploye();
        System.out.println("-------EmployeeB-------");
        for (EmployeeB employeeB : list2) {
            System.out.println(employeeB.getId());
        }
    }
}
```

- 迪米特法则的核心是降低类之间的耦合
-  但是注意:由于每个类都减少了不必要的依赖，因此迪米特法则只是要求降低类间(对象间)耦合关系，并不是要求完全没有依赖关系

### 合成复用原则（Composite Reuse Principle）

**原则是尽量使用合成、聚合方式。而不是继承。**

```java
// 继承（泛化）
class A {
  method1();
  method2();
  method3();
}
class B exntends A {} 
// 这样就是依赖
class A {
  method1(){};
  method2(){};
  method3(){};
}
class B {
  method(A a){
    a.method();
  }
} 
// 聚合 通过set进行设置
class A {
  method1(){};
  method2(){};
  method3(){};
}
class B {
 	private A a;
  setA(A a){};
} 
// 组合 这样在B有新实例的时候就自动实例化了A
class A {
  method1(){};
  method2(){};
  method3(){};
}
class B {
  private A a = new A();
  
} 
```

- 找出应用中可能需要变化之处，把它们独立出来，不要和那些不需要变化的代码混在一起。
-  针对接口编程，而不是针对实现编程
  -  为了交互对象之间的松耦合设计而努力

## 2. UML

**Unified Modeling Language**

UML 本身是一套符号的规定，就像数学符号和化学符号一样，这些符号用于描述软件模型中的各个元素和他 们之间的关系，比如类、接口、实现、泛化、依赖、组合、聚合等，如右图:

![](https://raw.githubusercontent.com/chihokyo/image_host/master/20210301171039.png)

类之间的关系有6种。

**依赖，泛化（继承），实现，关联，聚合，组合。**

#### UML图

- 用例图(usecase)
- 静态结构图:**类图**、对象图、包图、组件图、部署图 
- 动态行为图:交互图(时序图与协作图)、状态图、活动图

#### Dependence 依赖关系

只要在类中用到了对象，他们就存在依赖关系。如果没有对象，编译无法通过。

```java
class PersonServiceBeanDep {
    private PersonDaoDep personDaoDep;
    public void save(PersonDep pd) {
        
    }
    public IDCardDep getIDCardDep(Integer personID) {
        return null;
    }

    public void modify() {
        DepartmentDep departmentDep = new DepartmentDep();
    }
}

class PersonDaoDep {}
class IDCardDep {}
class PersonDep {}
class DepartmentDep {}
```



#### Generalization 泛化关系（继承关系，依赖关系特例）

实际上就是上面的特例。存在关系，就是一种依赖关系。而继承关系，就是泛化关系。

```java
/**
 * 泛化关系
 * ー▷
 * 有继承就是一种泛化关系
 * 1) 泛化关系实际上就是继承关系
 * 2) 如果A类继承了B类，我们就说A和B存在泛化关系
 */

abstract class DaoSupportGne {
    public void sava(Object entity){

    }
    public void delete(Object id) {
        
    }
}

class PersonServiceBeanGne extends DaoSupportGne {

}
```



#### Implementation 实现关系（依赖关系特例）

就是B实现了A的接口。依赖关系特例。

```java
/**
 * 实现关系
 * 
 * 就是接口实现而已
 * ・・・・▷
 */
interface PersonServiceImpl {
    public void delete(Integer id);
}

class PersonServiceBeanImpl implements PersonServiceImpl {
    @Override
    public void delete(Integer id) {
        
    }
}
```



#### Association 关联关系 （依赖关系特例）

**单向1对1关系**

```java
class PersonAssoA {
    private IDCardAsso card;
}

class IDCardAsso {}
```

**双向1对1关系**

```java
class PersonAssoB {
    private IDCardAssoB card;
}

class IDCardAssoB {
    private PersonAssoB p;
}
```

#### Aggregation 聚合关系

```java
/**
 * 聚合关系
 * 是一种整体和部分的关系。关联关系的特例。
 * !!这里要和组合关系作为对比，这里的鼠标和显示器都是可以分离出来了
 * 所以具有导航性，多重性
 * 空心菱形
 */
class ComputerAgg {
    private MouseAgg mouse;
    private MonitorAgg monitor;

    public void setMouse(MouseAgg mouse) {
        this.mouse = mouse;
    }
    public void setMonitor(MonitorAgg monitor) {
        this.monitor = monitor;
    }
}

class MouseAgg {}
class MonitorAgg {}
```

#### Composition 组合关系

```java
class ComputerCom {
    // 这里就是一个组合，这个mouse 就是不可获取的
    private MouseCom mouse = new MouseCom();
    private MonitorCom monitor;

    public void setMouse(MouseCom mouse) {
        this.mouse = mouse;
    }
    public void setMonitor(MonitorCom monitor) {
        this.monitor = monitor;
    }
}

class MouseCom {}
class MonitorCom {}
```

**组合和聚合的区别就在于，实例的是否可以分开。**

## 3. 设计模式

**什么是设计模式呢？**

> 1. 设计模式是程序员在面对同类软件工程设计问题所总结出来的有用的经验，模式不是代码，而是某类问题的通 用解决方案，**设计模式(Design pattern)代表了最佳的实践**。这些解决方案是众多软件开发人员经过相当长的 一段时间的试验和错误总结出来的。
> 2.  设计模式的本质提高软件的维护性，通用性和扩展性，并降低软件的复杂度。
> 3.  <<设计模式>> 是经典的书，作者是 Erich Gamma、Richard Helm、Ralph Johnson 和 John Vlissides Design(俗称 “四人组 GOF”)
> 4. 设计模式并不局限于某种语言，java，php，c++ 都有设计模式.

**设计模式分类？**

虽然这里写了23种，但是不限于这23种。

- 创建型
  - 单例，抽象工厂，原型，建造者，工厂
- 结构型
	- 适配器，桥接，装饰，组合，外观，享元，代理
- 行为型
	- 模块方法，命令，访问者，迭代器，观察者，中介者，备忘录，解释器，状态，策略，职责链


### 单例模式（Singleton）

> 就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例。并且该类只提供一个取得其对象实例的方法(静态方法)。

实现方式，竟然有8种。一个个写吧。

- **饿汉式(静态常量)**
- **饿汉式(静态代码块)**
- 懒汉式(线程不安全)
- 懒汉式(线程安全，同步方法)
- 懒汉式(线程安全，同步代码块) ❌
- **双重检查**
- **静态内部类**
- **枚举**

加粗的是推荐实现的

**<u>饿汉式(静态常量)</u>**

1. 优点:这种写法比较简单，就是在**类装载的时候就完成实例化（static）**。避免了线程同步问题。
2.  缺点:在类装载的时候就完成实例化，没有达到LazyLoading的效果。如果从始至终从未使用过这个实例，则会造成**内存**的浪费
3. 这种方式基于**classloder机制避免了多线程的同步问题**，不过，instance在**类装载**时就实例化，在单例模式中大 多数都是调用 getInstance 方法，但是导致类装载的原因有很多种，因此不能确定有其他的方式(或者其他的静 态方法)导致类装载，这时候初始化 instance 就没有达到 lazy loading 的效果

```java
class SingleTon {
    // 1. 构造器私有化（防止new）
    private SingleTon() {

    }
    // 2. 类的内部创建对象
    private final static SingleTon instance = new SingleTon();

    // 3. 暴露一个静态公共方法（为了获得实例对象）
    public static SingleTon getInstance() {
        return instance;
    }
}
```

**<u>饿汉式(静态代码块)</u>**

本质和上面一样，只不过写在了static代码块里面

```java
class SingleTon {
    private SingleTon(){

    }
    private static SingleTon instance;
    // 区别于上一个静态常量 这里使用的是静态代码块
    // 类加载的时候自动加载，所以会消耗一点资源
    static {
        instance = new SingleTon();
    }
    public static SingleTon getInstance() {
        return instance;
    }
}
```

**懒汉式(线程不安全)**

1. 起到了**LazyLoading**的效果，但是只能在单线程下使用。
2.  如果在多线程下，一个线程进入了if(singleton==null)判断语句块，还未来得及往下执行，另一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程环境下不可使用这种方式 
3. 结论:在实际开发中，不要使用这种方式.

```java
class Singleton {
    // 静态的实例
    private static Singleton instance;
    private Singleton() {}
    // 提供一个公共方法 
    // 使用这个方法的时候，才回去创建instance
    // 不同于上面的饿汉是类加载就创建了
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```

**懒汉式(线程安全，同步方法)**

1. 解决了线程安全问题
2. 效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步。而其实这个方法只执行一次实例化代码就够了，后面的想获得该类实例，直接 return 就行了。方法进行同步效率太低 
3. 结论:在实际开发中，不推荐使用这种方式

```java
class Singleton {
    private static Singleton instance; 
    private Singleton() {}
    // 提供一个静态的公有方法
    // 加入同步处理的代码，解决线程安全问题 
    // 即懒汉式
    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance; 
    }
}

```

**懒汉式(线程安全，同步代码块)**❌

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
```

**双重检查**

推荐使用！

```
1) Double-Check 概念是多线程开发中常使用到的，如代码中所示，我们进行了两次 if (singleton == null)检查，这 样就可以保证线程安全了。
2) 这样，实例化代码只用执行一次，后面再次访问时，判断if(singleton==null)，直接return实例化对象，也避 免的反复进行方法同步.
3) 线程安全;延迟加载;效率较高
4) 结论:在实际开发中，推荐使用这种单例设计模式
```



```java
lass Singleton {
    // volatile 暂时可以理解成简易版本的 synchronized
    private static volatile Singleton instance;

    private Singleton() {
    }

    // 提供一个静态的公有方法，加入双重检查代码，
    // 解决线程安全问题, 同时解决懒加载问题
    public static synchronized Singleton getInstance() {
        // 第一层判断 第一次实例化之后 第二次走这里就会直接return instance
        // 如果为空就会进去
        if (instance == null) {
            // 线程安全判断
            // 这段代码是同步代码，只能是1个线程进去之后
            // 保证了只能是1个线程在下面的synchronized执行
            // 加入有第2个线程已经进入到了这里 第1个还没有new到
            // 但是下面这段代码是线程安全的，只能保证1个 所以在等待
            synchronized (Singleton.class) {
                // 这时候第1个出去了，第2个等待之后进来了
                // 再一次的进行判断，发现1个实例已经创建了
                // 所以也不会多于的创建新的实例对象
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```

**静态内部类**

```
1) 这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
2) 静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载 SingletonInstance 类，从而完成 Singleton 的实例化。
3) 类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
4) 优点:避免了线程不安全，利用静态内部类特点实现延迟加载，效率高
5) 结论:推荐使用.
```



```java
class Singleton {
    private static volatile Singleton instance;

    // 构造器私有化
    private Singleton() {

    }

    // 其实这里就是一个静态内部类，该类有一个属性 Singleton
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    // 提供一个静态的公有方法，直接返回 SingletonInstance.INSTANCE
    public static synchronized Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
```

**枚举类实现**

1. 这借助JDK1.5中添加的枚举来实现单例模式。不仅能避免多线程同步问题，而且还能防止反序列化重新创建 新的对象。
2. 这种方式是**EffectiveJava**作者**JoshBloch**提倡的方式
3.  结论:**推荐使用**

```java
/**
 * 枚举类实现
 */
public class SingleTonTest08 {
    public static void main(String[] args) {
        System.out.println("******测试-枚举类实现******");
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        System.out.println(instance.hashCode());
        System.out.println(instance2.hashCode());
        instance.method();
        instance2.method();
    }
}

// 使用枚举可以实现单例，这个是Effective java 作者推荐
enum Singleton {
    // 属性 因为在enum只有1个属性 保证是单例
    INSTANCE;
    public void method() {
        System.out.println("OK");
    }
}
```

