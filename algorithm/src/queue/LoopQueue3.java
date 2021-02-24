package queue;

/**
 * 实现一个队列（不使用size）
 */
public class LoopQueue3<E> implements Queue<E> {

    private E[] data;
    private int front, tail;

    @SuppressWarnings("unchecked")
    public LoopQueue3(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue3() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        // 如果 tail >= front 那么队列实际容量就是 tail - front
        // 如果 tail < front 那么说明已经循环，队列实际容量就是
        // tail - front + data.length
        return tail >= front ? tail - front : tail - front + data.length;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        // 暂存
        E ret = data[front];
        // 手动清空
        data[front] = null;
        // 维护顺序
        front = (front + 1) % data.length;
        // 判断震荡
        if (getSize() == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }
        return data[front];
    }

    @SuppressWarnings("unchecked")
    public void resize(int newCapacity) {
        // 这里记得+
        E[] newData = (E[]) new Object[newCapacity + 1];
        int sz = getSize();
        for (int i = 0; i < sz; i++) {
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = sz;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        // 遍历方式要注意 front 到 tail
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if ((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

}
