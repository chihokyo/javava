/**
 * 设计类就是设计类的成员
 * 
 * 属性 = 成员变量 = field =  (字段，域)
 * 方法 = 成员方法 = 函数（c语言里面）= method
 * 创建类的对象 = 实例化 = 实例化一个类 = 对象也是一个实例
 * 
 * 面向对象的实现
 */
public class PersonTest {
    public static void main(String[] args) {
        /*********************************************/
        // 创建类的对象(实例化)
        Person p1 = new Person();

        // 调用类属性和方法
        p1.name = "Tom";
        p1.age = 19;
        p1.isMale = false;

        System.out.println(p1.name);
        System.out.println(p1.age);
        System.out.println(p1.isMale);

        p1.eat();
        p1.sleep();
        p1.speak("Chinese");

        /*********************************************/

        // 如果创建了一个类的多个对象，则每个对象都拥有独立的一套类的属性（非 static）
        // 如果修改了一个属性a 不影响其他对象属性的值
        Person p2 = new Person();
        System.out.println(p2); // Person@3764951d
        System.out.println(p2.name); // null
        System.out.println(p2.age); // 0

        /*********************************************/

        Person p3 = new Person();
        p3 = p1;
        // !! 这里相当于把p1的地址（内存指针）给了p3，所以p1的指向和p3指向都是同一片堆空间的对象实体
        // 所以接下来对p3的数据操作都讲影响到p1的数据
        System.out.println(p3.name); // Tom
        System.out.println(p3.age); // 19
        p3.name = "Amy";
        System.out.println(p3.name); // Amy
        System.out.println(p1.name); // Amy

        /*********************************************/

        // 内存解析
        // 1 p1 这种局部变量储存在 → 栈stack里面
        // 2 new Person的时候会在heap 堆里面开辟一片空间
        // 3 开辟空间的时候如果有属性设置数据，这时候就是默认数据，没有默认值，就是初始化的那个数据接口（0/0.0 null false什么的）
        // 4 如果有默认的设置的属性值，这个属性值应该是在内存里的方法区 method area
        // 5 1里面的局部变量p1实际上是一个地址 指向了 heap里面的对象
        // 6 然后就是这样 其实就是三个区域内分别放置值进行指向
        // Person p3 = new Person();
        // p3 = p1;
        // 这一句要注意的就是，其实p3根本就不是对象，本身就是一个局部变量，存储了一个地址，这个地址指向了p1的对象

    }
}


/**
 * 人的类
 */
class Person {
    // 属性 声明
    String name;
    int age;
    boolean isMale;

    // 方法 
    public void eat () {
        System.out.println("eat");
    }

    public void sleep () {
        System.out.println("sleep");
    }

    public void speak (String language) {
        System.out.println("speak" + language);
    } 
}
