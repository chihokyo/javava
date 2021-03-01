/**
 * 类中的方法的使用
 * 
 * 1 关于声明方法
 *  public void eat() {}
 *  public void sleep(int hour) {}
 *  public String getName() {}
 *  public String getNation(String nation) {}
 * 2 方法的声明
 *  权限修饰符 + 返回值类型 + 方法名 （形参列表）{ 方法体 };
 *  void 表示返回值 是什么类型的，void 表示没有返回值or return；这样也可以  
 *  注意 static final abstract 修饰方法
 * 3 有无返回值
 *      如果方法有返回值
 *          ①则必须在方法里声明返回值的类型 
 *          ②return 需要返回指定类型的属于（变量or常量）
 *      如果方法没
 *          ①则方法声明需要void来表示
 *          ②通常没有可以不用，要使用直接return;
 * 4 形参列表
 * 5 方法体 关于方法功能的说明
 * 6 return 使用
 *      适用范围： 在方法体里中体现
 *      1 结束方法
 *      2 针对有返回值类型的方法 使用return 进行返回所需要的数据   
 *      3 return 后面不要写语句
 *      4 特殊的用法，可以在 return 里面写上递归的调用方法
 */     


public class CustomerTest {
    public static void main(String[] args) {
        
    }
    
}

/**
 * InnerCustomerTest
 */
class InnerCustomerTest {

    String name;
    int age;
    boolean isMale;

    public void eat() {
        System.out.println("eat");
        return;
    }

    public void sleep(int hour) {
        System.out.println("sleep" + hour);
    }

    public String getName() {
        return name;
    }

    public String getNation(String nation) {
        String info = "china" + nation;
        return info;

    }
}
