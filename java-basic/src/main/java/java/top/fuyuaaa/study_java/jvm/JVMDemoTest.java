package java.top.fuyuaaa.study_java.jvm;

/**
 * @author: fuyuaaaaa
 * @description: 获取当先空余内存和总内存
 * @program: study-java
 * @creat: 2018-11-11 15:14
 **/
public class JVMDemoTest {

    public static String toMemoryInfo() {
        Runtime runtime = Runtime.getRuntime();
        int freeMemory = (int) (runtime.freeMemory() / 1024 / 1024);
        int totalMemory = (int) (runtime.totalMemory() / 1024 / 1024);
        return freeMemory + "M/" + totalMemory + "M(free/total)";
    }

    public static void main(String[] args) {
        System.out.println(toMemoryInfo());
    }
}
