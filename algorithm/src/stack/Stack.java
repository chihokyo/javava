package stack;

public interface Stack<E> {
    // 长度
    int getSize();

    // 是否为空
    boolean isEmpty();

    // 押入栈
    void push(E e);

    // 弹出栈
    E pop();

    // 查看栈顶元素
    E peek();
    
}
