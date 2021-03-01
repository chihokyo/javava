package queue;

/**
 * 实现一个循环队列（不再使用动态数组）
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size; // 有多少个元素（只使用 front 和 tail 也可以计算大小）

    @SuppressWarnings("unchecked")
    public LoopQueue(int capacity) {
        // 因为需要刻意浪费一个空间（tail + 1 判断是否满），所以容量需要 + 1
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    // 循环队列容量
    public int getCapacity() {
        // 因为浪费了1个空间，所以容量需要 - 1
        return data.length - 1;
    }

    // 循环队列大小
    @Override
    public int getSize() {
        return size;
    }

    // 循环队列是否为空
    @Override
    public boolean isEmpty() {
        // 头尾是否一致
        return front == tail;
    }

    // 入队
    @Override
    public void enqueue(E e) {
        // 判断队列是否已满
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        // 循环队列，取余是关键 这样才能循环
        tail = (tail + 1) % data.length;
        size++;

    }

    // 出队
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
        size--;
        // 判断震荡
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty");
        }
        return data[front];
    }

    /**
     * 扩容or缩容
     * 
     * @param newCapacity
     */
    @SuppressWarnings("unchecked")

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            // 有可能队首元素不是0
            // 新队列从队首开始计算
            // 所以 newData[0] -- data[front]; newData[1] -- data[front + 1]
            // data所有元素 是有front 偏移
            // 又因为是循环，所以会产生越界。所以需要和长度取余
            newData[i] = data[(i + front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
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
