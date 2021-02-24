package queue;

import array.NewArray;

/**
 * 利用自定义的动态数组设计一个队列
 */
public class ArrayQueue<E> implements Queue<E> {

    private NewArray<E> array;

    public ArrayQueue(int capacity) {
        array = new NewArray<>(capacity);
    }

    public ArrayQueue() {
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
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    // 获取容量
    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        // 表明队列最前是在左边
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            // 判断是否是最后一个否则，
            if (i != array.getSize() - 1) {
                res.append(",");
            }
        }
        // 表示尾巴在最右边
        res.append("] tail");
        return res.toString();
    }

}
