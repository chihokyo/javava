package queue;

import java.util.Random;

import org.junit.Test;

/**
 * 测试一下自定义的队列
 */
public class TestQueue {
    public static void main(String[] args) {

        int opCount = 100000; // 十万级

        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testSpeedQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time: " + time1 + "s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testSpeedQueue(loopQueue, opCount);
        System.out.println("LoopQueue, time: " + time2 + " s");

        // 数组队列完败，主要是dequeue这个级别就是0(n^2)
        // ArrayQueue, time: 49.963594867 s
        // LoopQueue, time: 0.028540527 s
    }

    /**
     * 封装一个统一测试时间工具
     * 
     * @param q       传入Integer类型的队列（多态性 既可以是循环还可以是数组）
     * @param opCount 操作数量
     * @return
     */
    private static double testSpeedQueue(Queue<Integer> q, int opCount) {

        long startTime = System.nanoTime();

        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000_000_000.0;
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
