package queue;

import org.junit.Test;

/**
 * 测试一下自定义的队列
 */
public class TestQueue {
    public static void main(String[] args) {

    }

    // 测试一个数组队列
    @Test
    public void testArrayQueue() {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            // 每隔三个进行一次dequeue
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }

    // 测试一个循环队列
    @Test
    public void testLoopQueue() {
        LoopQueue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            // 每隔三个进行一次dequeue
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
