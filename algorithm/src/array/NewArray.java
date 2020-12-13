package array;
/**
 * 自己封装一个数组
 * 1 capacity 表示数组的总容量，初始化大小。
 * 2 size 表示实际容量。数组第一个没有元素的位置。
 * 3 data 成员
 */
public class NewArray {

    private int[] data; // 成员属性
    private int size; // 有效元素
    // 为什么不写 capacity 因为这个可以通过data.length 推断出来

    /**
     * 构造函数 传入数组的容量构造 NewArray
     * 
     * @param capacity 容量
     */
    public NewArray(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 空参构造函数 默认就数组容量为10
     */
    public NewArray() {
        this(10);
    }

    /**
     * 获取数组元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断是否为空数组
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
