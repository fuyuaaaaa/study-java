package top.fuyuaaa.study.jvm;

public class TestMemoryAllocate {

    private static final int _1MB = 1024 * 1024;


    /**
     * 对象优先在Eden分配，当出现不能分配是会进行minorGc，如果gc存活的对象大小大于Survivor的大小，直接进入老年代（分配担保机制）。
     * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * ps: 堆大小20M, 新生代10M, 打印GC日志, Eden:SurvivorFrom:SurvivorTo=8:1:1
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * -XX:PretenureSizeThreshold=3145728  -> 对象大小大于3MB直接在老年代分配，避免在Eden，Survivor来回复制
     * 改参数只对Serial和PaeNew生效
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }


    public static void main(String[] args) {
//        testAllocation();
        System.out.println("========================================");
        testPretenureSizeThreshold();
    }
}
