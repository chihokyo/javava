/**
 * ConstructorTest
 * 创建对象，建造，构造。
 * 构造器作用↓
 * 1 创建对象
 * 2 创建对象同时对属性进行初始化
 * 构造器说明↓
 * 1 如果没有显示的定义类的构造器的话，那么系统就会默认提供一个空参的构造器
 * 2 定义构造器的格式，权限修饰符与类同名
 * 3 可以定义多个构造器（构成重载）
 * 4 显示定义的话，系统就不会提供空参构造器
 * 5 一个类中，至少都会有一个构造器
 * 6 默认构造器权限和类的权限相一致
 * 
 * 构造器其实不是方法，写起来格式不一样。最重要的是功能不一样，构造器主要是来造对象的。
 * 而方法就是一个功能，体现的是一个功能。而构造器主要功能只有构造对象
 * 所以在类里面的主要步骤应该是 1 写类 2 构造器生成对象 3 调用对象的属性&方法
 * 
 */

public class ConstructorTest {

    public static void main(String[] args) {
        // Q: InnerPersonTest();是什么呢，也不是函数，既然是类，还要()
        // A: 其实是一个构造器 创建类的对象：new + 构造器
        // 在执行 new InnerPersonTest(); 的时候调用下面的构造器了
        InnerPersonTest p = new InnerPersonTest();
        p.eat();

        InnerPersonTest p1 = new InnerPersonTest("Amy");
        System.out.println("p1.name:" + p1.name);

    }
}

/**
 * 举例说明构造器
 */
class InnerPersonTest {
    // 属性
    String name;
    int age;

    /********************************************/
    /*
     * 如果没有定义构造器，那么系统会默认给一个空参构造器 一旦显示定义了构造器。那么系统就不会提供。 /
     ********************************************/

    // 构造器1
    public InnerPersonTest() {
        System.out.println("构造器：InnerPersonTest(){}");
    }

    // 构造器2
    public InnerPersonTest(String n) {
        // 可以在构造对象的同时，进行属性初始化
        name = n;
    }

    // 构造器3
    public InnerPersonTest(String n, int m) {
        // 多个构造器之间构成重载
        name = n;
        age = m;
    }

    // 方法
    public void eat() {
        System.out.println("eat");
    }
}