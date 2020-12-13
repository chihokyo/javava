package array;

/**
 * 自己封装一个数组 1 capacity 表示数组的总容量，初始化大小。 2 size 表示实际容量。数组第一个没有元素的位置。 3 data 成员
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

    /**
     * 所有元素后添加一个元素
     * 
     * @param e 元素
     */
    public void addLast(int e) {
        // if (size == data.length) {
        //     throw new IllegalArgumentException("addlast failed.Array is full");
        // }

        // data[size] = e;
        // size ++;
        // // 虽然也可以写成 ↓ 但是并不推荐，可读性差
        // // data[size++] = e;
        
        // 写完add之后可以修改成
        add(size, e); // size表达的就是长度也就是最后一个位置
    }

    /**
     * 在数组最前面添加一个元素
     * 
     * @param e 元素
     */
    public void addFirst(int e) {
        add(0, e);
    }

    /**
     * 向指定位置传递元素
     * 
     * @param index
     * @param e
     */
    public void add(int index, int e) {
        if (size == data.length) {
            throw new IllegalArgumentException("addlast failed.Array is full");
        }
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("addlast failed.Require index < 0 and index > size ");
        }
        for (int i = size - 1; i < index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;

    }
}
