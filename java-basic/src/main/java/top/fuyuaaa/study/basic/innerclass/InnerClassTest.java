package top.fuyuaaa.study.basic.innerclass;

/**
 * 内部类
 * Java 类中不仅可以定义变量和方法，还可以定义类，这样定义在类内部的类就被称为内部类。根
 * 据定义的方式不同，内部类分为静态内部类，成员内部类，局部内部类，匿名内部类四种。
 *
 * @author : fuyuaaa
 * @date : 2020-05-20 15:01
 */
@SuppressWarnings("all")
public class InnerClassTest {

    private static String staticPrivateField = "staticPrivateField";
    private static void staticPrivateMethod(){

    }

    /**
     * 静态内部类
     * 可以访问外部类静态属性和静态方法
     */
    public static class StaticInnerClass {

        public void testMethod(){
            System.out.println(staticPrivateField);
            staticPrivateMethod();
        }
    }
}
