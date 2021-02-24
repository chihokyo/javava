package array;

import org.junit.Test;

/**
 * 关于数组
 * 
 */
public class Array {
    public static void main(String[] args) {

        NewArray<Integer> arr = new NewArray<>(20);

        // 添加
        for (int i = 0; i < 10; i++) {
            arr.addFirst(i);
        }
        System.out.println(arr);
        arr.add(2, 23);
        System.out.println(arr);
        arr.addFirst(-99);
        System.out.println(arr);

        // 修改
        arr.set(0, 11);

        // 查询
        System.out.println(arr);
        System.out.println(arr.get(3));
        System.out.println(arr.find(4));

        // 删除
        arr.remove(2);
        System.out.println(arr);
        arr.removeFirst();
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);
        arr.removeElement(23);
        System.out.println(arr);

    }

    /**
     * 测试泛型类（Student）
     */
    @Test
    public void testGeneric() {

        NewArray<Student> stuArr = new NewArray<>();
        stuArr.addLast(new Student("Alice", 89));
        stuArr.addLast(new Student("Alice2", 44));
        stuArr.addLast(new Student("Tom", 19));
        System.out.println(stuArr);
    }

    /**
     * 测试是否是动态数组
     */
    @Test
    public void testDynamic() {
        NewArray<Integer> arr = new NewArray<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(100);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
    }

}
