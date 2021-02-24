package basis;
/**
 * while循环
 * 最重要的就是防止死循环的问题
 * while 和 for 是可以相互转换
 * 1. 不同点就是下面的 作用域 的问题
 * 2. 书写起来的时候结构有时候会分哪个看起来更舒服一点
 */

public class WhileLoop {
    public static void main(String[] args) {
        int i = 1; // 这个i在for里面是内部作用域，但是while是在外面的
        while (i <= 100) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i++; // 迭代条件
        }

        System.out.println(i);// 101 定义在外
    }
}
