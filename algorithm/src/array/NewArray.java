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
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;

    }

    /**
     * 获取指定位置的元素 既可以检查index的有效性，又可以体现封装性，不会把没使用的空间暴露出去
     * 
     * @param index 指定位置
     * @return
     */
    int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal");
        }
        return data[index];
    }

    /**
     * 修改index指定位置的元素
     * 
     * @param index 指定位置
     * @param e     元素
     */
    void set(int index, int e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否有元素
     * 
     * @param e 查找元素e
     * @return
     */
    public boolean contains(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找数组中元素e所在的index，如果不存在返回-1 查找第一个就返回了
     * 
     * @param e
     * @return
     */
    public int find(int e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定index位置的元素 并返回元素值
     * 
     * @param index 删除的index
     * @return
     */
    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal");
        }
        int ret = data[index];
        // index之后的元素都向前移动
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--; // 删除之后 size要-1 虽然size还有值，但是size其实那个值永远不会可见。（其实初始化的时候那个位置就是0了）
        return ret;
    }

    // 删除第一个元素
    public int removeFirst() {
        return remove(0);
    }

    // 删除最后一个元素
    public int removeLast() {
        return remove(size - 1);
    }

    /**
     * 删除指定元素(删除第一个)
     * 
     * @param e 需要删除的元素
     */
    public void removeElement(int e) {
        // 先查找有没有这个元素
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size=%d, capacity = %d\n", size, data.length));
        res.append("[");
        // 这里记得用的是容量，而不是data.length
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }
}
