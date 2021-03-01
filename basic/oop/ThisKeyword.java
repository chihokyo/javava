/**
 * this 关键字的使用
 *      1 this可以用来修饰 属性 方法 构造器
 *      2 this就是指【当前对象】
 *      3 在类的方法中，可以通过this.属性or方法 来调用当前对象属性和方法
 *      但是通常情况下都会选择省略
 * 
 * this 调用构造器
 *      1 在类的构造器中可以通过 this(形参列表) 方式，调用本类的【其他】构造器
 *      2 构造器中不能通过 this() 调用自己
 *      3 虽然构造器连续调用了3个，逻辑上，但实际上还是只构造了一个对象
 *      4 如果n个构造器 只能有n-1个使用 this(形参列表)
 *      5 只要调用其他构造器，就必须在首行
 *      6 所以按照5的理论，那么就不能写在构造器里面调用多个构造器
 * 
 * Q:为什么要写？
 * A:重名的情况下默认可以不写，既然重名了，那就是要写，形参和实参的对决。
 * 
 */

public class ThisKeyword {
    public static void main(String[] args) {
        InnerThisKeyword i1 = new InnerThisKeyword();
        i1.setName("yesss");
        i1.eat();

        System.out.println("开始测试构造器的代码");

        InnerThisKeyword i2 = new InnerThisKeyword("Amy", 99);
        System.out.println(i2.getAge());

    }
}

class InnerThisKeyword {

    private String name;
    private int age;

    // this 构造器1
    public InnerThisKeyword() {
        // 如果这时候初始化InnerThisKeyword，需要写个40行代码
        // 思路1 写一个公用的方法
        // 思路2 写一个公用的构造器 也就是说冗长的代码写在一个构造器下
        // 其他的构造器只要调用这一个构造器里的那些冗长的代码就好在下面一个构造器里
        this.eat();
        String infoSample = "这里相当于40行代码";
        System.out.println(infoSample);
    }

    // this 构造器2
    public InnerThisKeyword(String name) {
        // 如果这时候初始化InnerThisKeyword，需要写个40行代码
        // 举例
        this(); // 这样直接去执行上一个构造器里那些冗长的代码 必须在首行代码上 first statement
        this.name = name;
    }

    // this 构造器3
    public InnerThisKeyword(int age) {
        // 如果这时候初始化InnerThisKeyword，需要写个40行代码
        // 举例
        this();
        this.age = age;
    }

    // this 构造器4
    public InnerThisKeyword(String name, int age) {
        // 如果这时候初始化InnerThisKeyword，需要写个40行代码
        // 如果这个时候不是想调用第一个构造器的代码，而是想调用 public InnerThisKeyword(int age) {} 构造器的代码
        // 需要指定相应的参数
        this(age);
        this.name = name;
        // this.age = age; 那么这一行就多余的了
        // 因为已经调用了 public InnerThisKeyword(int age) {} 里面的 this.age = age;
    }

    // this 属性
    public void setName(String name) {

        // name = name; 【NG】这样是会出错的
        // 表示前面的this.name 是属性 后面的name是形参
        // Q:为什么要写？
        // A:重名的情况下默认可以不写，既然重名了，那就是要写，形参和实参的对决。
        this.name = name;
    }

    // this 方法
    public void eat() {
        System.out.println("eat");
        study();
        this.study(); // 可以省
    }

    public void study() {
        System.out.println("study");
    }

    public int getAge() {
        return age;
    }

}
