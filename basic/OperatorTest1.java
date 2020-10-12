/**
 * 小练习，打印出1个数字的个位，十位，百位。
 */
public class OperatorTest1 {
    public static void main(String[] args) {
        int num = 189;

        int bai = num / 100;
        int shi = num % 100 / 10;
        int ge = num % 10;

    System.out.println("百位是：" + bai);
    System.out.println("十位是：" + shi);
    System.out.println("个位是：" + ge);
    }
}