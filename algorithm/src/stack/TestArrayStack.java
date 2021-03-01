package stack;
/**
 * 测试基于动态数组实现的栈stack结构
 */
public class TestArrayStack {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.getSize());
        System.out.println(stack.getCapacity());
    }
}
