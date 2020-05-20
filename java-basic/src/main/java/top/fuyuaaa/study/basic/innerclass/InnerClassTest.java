package top.fuyuaaa.study.basic.innerclass;

import java.util.ArrayList;
import java.util.Comparator;

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

    private static void staticPrivateMethod() {

    }

    /**
     * 静态内部类
     * 可以访问外部类静态属性和静态方法
     */
    public static class StaticInnerClass {

        public void testMethod() {
            System.out.println(staticPrivateField);
            staticPrivateMethod();
        }
    }

    /**
     * 成员内部类
     * 不能定义静态属性和静态方法, 但是可以定义 final static 属性
     */
    public class MemberInnerClass {

        //如果是编译期初始化的final属性就可以存在于成员内部类
        //不可以这样写
        //private static String staticField = "";
        private final static String finalStaticField = "";
//        private final static String finalStaticField2 = "" + Math.random();

        public void testMethod() {
            System.out.println(staticPrivateField);
            staticPrivateMethod();
        }
    }

    /**
     * 局部内部类
     * 定义在方法里的内部类
     */
    public void testMethodInnerClass() {
        class methodInnerClass {

        }
    }

    /**
     * 匿名内部类
     */
    @SuppressWarnings("unchecked")
    public void testUnnamedInnerClass() {
        ArrayList<Integer> arrayList = new ArrayList<>(10);
        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });


        Thread thread = new Thread(){

        };
    }

}
