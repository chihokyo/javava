package linkedlist;
/**
 * 一个链表类
 * 使用Dummy虚拟头节点
 */
public class DummyLinkedList<E> {

    public class Node {

        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public DummyLinkedList() {
        dummyHead = new Node();
        size = 0;
    }

    // 获取链表元素个数
    public int getSize() {
        return size;
    }

    // 判断是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 指定位置添加元素 node
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index. ");
        }
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);
        size++;
    }

    // 链表头部添加元素 node
    public void addFirst(E e) {
        add(0, e);
    }
    // 链表尾部添加元素 node

    public void addLast(E e) {
        add(size, e);
    }
}
