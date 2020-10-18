/**
 * 方法的重载
 * 1 在同一个类中，允许存在一个以上的同名方法。只要参数个数和类型不同。
 * 2 方法名相同
 * 3 参数个数不同，参数类型不同
 * 除了以上，修改方法修饰符，返回值类型，形参变量名，方法体都没关系。
 * 只跟【方法名，参数个数，参数类型】
 * 4 在对象进行调用方法时，只会看方法名 + 参数列表（参数个数，参数类型）
 */
public class OverLoadTest {

    public static void main(String[] args) {
        OverLoadTest test1 = new OverLoadTest();
        test1.getSum(1, 2);// 有时候会强制进行类型提升
    }
    
    // 以下方法都是重载
    // public void getSum(int a, int b) {
    //     //return a + b;
    //     System.out.println("1");
    // }
    public void getSum(double a, double b) {
        // return a + b;
        System.out.println("2");
        
    }
    public void getSum(String a, int b) {
        System.out.println(a + b);
    }
    public void getSum(int a, String b) {
        System.out.println(a + b);
    }

    // 都不是
    // 1 只是改了形参，不算
    // public int getSum(int i, int j) {
    //     return a + b;
    // }

    // 2 只是改了返回类型 不算
    // public void getSum(int i, int j) {
    // }

    // 3 只是改了方法修饰符 不算
    // private int getSum(int a, int b) {
    //     return a + b;
    // }


}
