package linkedlist;

/**
 * 一个链表类 
 * 一个链表应该包含节点和next信息（指针，指向下一个节点）
 */
public class LinkedList<E> {

    // 用户无法访问内部Node，不需要了解链表底部实现
    // 用户知道链表是链式结构可以增删改查就可以

    // 这里原来作者写的是private 因为用户无需知道，但是这里会报错
    public class Node {

        // 2个成员变量
        public E e; // 元素
        public Node next; // 指向引用（下一个Node）

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 在链表头添加新元素
    public void addFirt(E e) {
        // // 方法一
        // // 新建一个node
        // Node node = new Node(e);
        // // 新链表下一个指向head
        // node.next = head;
        // // head现在就是node了
        // head = node;

        // 方法二
        // 相当于新建一个位置在head的node，然后让现在的head指向新node
        head = new Node(e, head);

        size++;
    }

    // 添加到指定位置
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }
        if (index == 0) {
            addFirt(e);
        } else {
            // head给了前一个
            Node prev = head;
            // 这里要找到index 前一个节点 也就是 index - 1
            // 这里刚开始我陷入了一个误区，就是把for里面的变量想象成链表的某个逻辑
            // 其实for 只是一个循环，只能决定循环次数。
            // 既然要知道index的前一个，那么循环次数也应该是next 前一个仅此而已 for里面和循环无关的
            for (int i = 0; i < index - 1; i++) {
                // 开始遍历 因为 index 如果是2的话，那么
                prev = prev.next;
            }
            // Node node = new Node(e);
            // node.next = prev.next;
            // prev.next = node;
            
            // 三句成一句
            prev.next = new Node(e, prev.next);
            size++;
        }
    }

    // 链表末尾新增元素e
    public void addLast(E e) {
        add(size, e);
    }

}
