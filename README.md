# 设计模式(Desing Patterns)

## 写在前面的

设计模式感觉蛮有用的，具体代码写逻辑的时候感觉算法有用。在实际上写一个项目的时候感觉设计模式有用。

## 七大原则

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

