/**
 * 类中属性的使用
 * 属性（成员变量） VS 局部变量
 * 
 * 1 相同点
 *      格式相同 （定义一样）
 *      规则相同 都是先声明
 *      变量都有其作用域
 * 
 * 
 * 2 不同点 
 *      ① 位置不同
 *          【属性（成员变量）】 直接定义在类｛｝内部
 *          【局部变量】 声明在方法内，方法形参，代码块内，构造器形参，构造器内部的形参 
 *      
 *      ② 权限修饰符不同
 *          【属性（成员变量）】可以在声明变量时指名权限修饰符并且使用
 *              常用的权限修饰符 private/public/protected/缺省 ---->>> 体现封装性
 *          【局部变量】不可以不可以不可以！（因为默认使用函数的）
 *      ③ 默认初始化值不一样
 *          【属性（成员变量）】都有的，参考以前的
 *          【局部变量】: 没有初始化的值。一定要显式赋值
 *          ※ 除了形参，因为形参那个地方只有函数被调用才会开始，调用的时候写入参数即可）
 *      ③ 加载位置不同
 *          【属性（成员变量）】对象内的 heap（非 static 因为在方法区）
 *          【局部变量】 局部变量 stack
 */
public class UserTest {
    public static void main(String[] args) {
        InnerUserTest u1 = new InnerUserTest();

        System.out.println(u1.name); // null
        System.out.println(u1.age); // 0
        System.out.println(u1.isMale); // false

        u1.speak("Chinese"); // 形参调用传入参数进行赋值 
    }
}


/**
 * InnerUserTest
 */
class InnerUserTest {
    // 成员变量 name age isMale
    public String name;
    int age;
    boolean isMale;

    // 局部变量 language
    public void speak(String language) {
        System.out.println("speak " + language);
    }
    public void eat() {
        // 局部变量 food
        String food = "pizza";
        //【NG】 局部变量没有默认值，肯定这样是错的。String food;
        //【NG】public String food = "pizza";（因为默认使用函数的） 也就是上面的 【public】 void eat
        System.out.println("eat" + food);
    }
}