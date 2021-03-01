package queue;

public interface Queue<E> {
    // 长度
    int getSize();

    // 是否为空
    boolean isEmpty();

    // 入列
    void enqueue(E e);

    // 出列
    E dequeue();

    // 查看头列
    E getFront();

}
