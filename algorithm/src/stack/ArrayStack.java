package stack;

import array.NewArray;

public class ArrayStack<E> implements Stack<E> {

    NewArray<E> array;

    public ArrayStack(int capacity) {
        array = new NewArray<>(capacity);
    }

    public ArrayStack() {
        array = new NewArray<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack");
        res.append("[");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            // 判断是否是最后一个否则，
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        // 表明栈顶是在右边
        res.append("] top");
        return res.toString();
    }
}
