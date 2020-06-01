package top.fuyuaaa.study.jvm;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OOMTest {


    /**
     * 堆内存OOM
     */
    @Test
    public void testHeapOOM() {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
            System.out.println(Runtime.getRuntime().freeMemory());
        }
    }

    /**
     * 栈内存OOM
     */
    @Test
    public void testStackOOM() {
        try {
            stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + stackLength);
            throw e;
        }
    }

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    private static final int _1MB = 1024 * 1024;

    /**
     * 对外内存OOM
     *
     * @throws IllegalAccessException
     */
    @Test
    public void testDirectMemoryOOM() throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            System.out.println("===");
            unsafe.allocateMemory(_1MB);
        }
    }
}

