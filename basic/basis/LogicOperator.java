package basis;

/**
 * 逻辑运算符 Logical operators 1 只能适用于boolean类型 && PK & || PK | 结论： 双 &&
 * 就是短路，只要满足前面一个条件，后面的代码就被无视掉了 所以下面的b2已经是false了，不影响结果。后面的代码也就不执行 num就是10
 */
public class LogicOperator {
    public static void main(String[] args) {
        // 区别 && PK &
        boolean b1 = true;
        b1 = false;
        int num1 = 10;
        if (b1 & (num1++ > 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        System.out.println("num1: " + num1); // num1: 11

        // 区别 && PK &
        boolean b2 = true;
        b2 = false;
        int num2 = 10;
        if (b2 && (num2++ > 0)) {
            System.out.println("YES2");
        } else {
            System.out.println("NO2");
        }
        System.out.println("num2: " + num2); // num1: 10

        // 区别 || PK |
        boolean b3 = false;
        b3 = true;
        int num3 = 10;
        if (b3 | (num3++ > 0)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
        System.out.println("num3: " + num3); // num3: 11

        // 区别 || PK |
        boolean b4 = false;
        b4 = true;
        int num4 = 10;
        if (b4 || (num4++ > 0)) {
            System.out.println("YES2");
        } else {
            System.out.println("NO2");
        }
        System.out.println("num4: " + num4); // num4: 10

    }
}
