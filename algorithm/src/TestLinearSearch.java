import org.junit.Test;

/**
 * 写一个测试类
 * 毫秒级的速度不足以证明
 * 所以需要亿级数据，但是百万数据的容量会给计算机带来空间压力
 * 所以就用一个循环，执行100次试一试
 */
public class TestLinearSearch {

    @Test
    public void testSearch() {
        // 数据规模 这样足以证明百万级别，千万级别线性关系 
        // 规模增长10倍，速度也差不多是10倍差异。
        int[] dataSize = {1_000_000, 10_000_000};

        for (int n : dataSize) {
            Integer[] arrayData = ArrayGeneraor.generateOrderedArray(n);
            long startTime = System.nanoTime();
            // 使用循环
            for (int i = 0; i < 100; i++)
            LinearSearch2.search(arrayData, n);
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 100_000_000.0;

            System.out.println("数据规模:" + n + ", 执行速度: " + time + " s");
        }

    }
}
