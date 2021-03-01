package linkedlist;

/**
 * 测试类 - 链表
 */
public class TestLinkedList {
    public static void main(String[] args) {
        NewLinkedList<Integer> linkedList = new NewLinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }

}
