package top.fuyuaaa.study.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

/**
 * 反射
 * <p>
 * Java反射机制
 * 在程序运行时，
 * 对于任意一个类，都能知道这个类的所有属性和方法，
 * 对于任意一个对象，都能调用它的任意一个方法和属性，
 * 这种动态获取信息和动态调用对象方法的功能被称为Java的反射机制。
 *
 * @author : fuyuaaa
 * @date : 2020-02-11 14:49
 */
@SuppressWarnings("all")
public class ReflectTest {

    @Getter
    @Setter
    private String privateParam;
    @Getter
    @Setter
    public String publicParam;
    @Getter
    @Setter
    String defaultParam;

    @Test
    public void testGetClassInfo() throws NoSuchFieldException, NoSuchMethodException {

        Class<ReflectTest> reflectTestClass = ReflectTest.class;
        //获取所有属性
        Field[] declaredFields = reflectTestClass.getDeclaredFields();
        //获取public属性
        Field[] field = declaredFields = reflectTestClass.getFields();


        //获取所有方法(子类所有)
        Method[] declaredMethods = reflectTestClass.getDeclaredMethods();
        //获取public方法(包括子类、父类)
        Method[] methods = reflectTestClass.getMethods();
        Method method = methods[0];
        //获取方法返回类型
        Class<?> returnType = method.getReturnType();
        //获取方法抛出异常类型
        Class<?>[] exceptionTypes = method.getExceptionTypes();


        Method setPrivateParam = reflectTestClass.getDeclaredMethod("setPrivateParam", String.class);
        //获取全部参数
        Parameter[] parameters = setPrivateParam.getParameters();
        Parameter parameter = parameters[0];
        //获取参数名, 如果是arg0, 可在编译参数加上-paramter
        String name = parameter.getName();
        //获取参数类型
        Class<?> type = parameter.getType();

    }


    private String testOperatePrivateMethod(String arg1, String arg2) {
        return arg1 + "&" + arg2;
    }

    @Test
    public void testOperatePrivateMethod() throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();
        //操作私有方法
        Method testPrivate = reflectTestClass.getDeclaredMethod("testOperatePrivateMethod", String.class, String.class);
        //获取方法访问权限
        testPrivate.setAccessible(true);
        System.out.println(testPrivate.invoke(reflectTest, "code", "dance"));
    }

    @Getter
    @Setter
    private String name = "code";

    @Test
    public void testOperatePrivateAttributes() throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();

        //修改私有属性的值
        Field name = reflectTestClass.getDeclaredField("name");
        //code
        System.out.println(reflectTest.getName());
        name.set(reflectTest, "code is worse than dance");
        //code is worse than dance
        System.out.println(reflectTest.getName());
    }

    @Getter
    @Setter
    private final String testFinalOne = "code";

    public String showTestFinalOne() {
        return testFinalOne;
    }

    @Test
    public void testOperateFinalAttributesOne() throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();

        //修改不了
        Field testFinalOne = reflectTestClass.getDeclaredField("testFinalOne");
        testFinalOne.setAccessible(true);
        //输出code
        System.out.println(reflectTest.getTestFinalOne());
        testFinalOne.set(reflectTest, "code is worse than dance");
        //输出code is worse than dance, 反射改了值, 但是常量的值不会被修改
        System.out.println(testFinalOne.get(reflectTest));
        //输出code
        System.out.println(reflectTest.showTestFinalOne());
    }
    //==========$华丽分隔符$==========  ==========$华丽分隔符$==========

    @Getter
    @Setter
    private final String testFinalTwo;

    //构造器初始化
    public ReflectTest() {
        testFinalTwo = "code";
    }
    public String showTestFinalTwo() {
        return testFinalTwo;
    }
    @Test
    public void testOperateFinalAttributesTwo() throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();

        //修改成功, 在构造器里定义final变量的值
        Field testFinalTwo = reflectTestClass.getDeclaredField("testFinalTwo");
        testFinalTwo.setAccessible(true);
        //输出code
        System.out.println(reflectTest.getTestFinalTwo());
        testFinalTwo.set(reflectTest, "code is worse than dance");
        //输出code is worse than dance
        System.out.println(reflectTest.showTestFinalTwo());
    }

    //==========$华丽分隔符$==========  ==========$华丽分隔符$==========

    //三目表达式
    @Getter
    @Setter
    private final String testFinalThree = null == null ? "code" : "";
    //这样写不行，基本类型会被计算
//    private final String testFinalThree = 1 == 1 ? "code" : "";


    public String showTestFinalThree() {
        return testFinalThree;
    }

    @Test
    public void testOperateFinalAttributes() throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> reflectTestClass = reflectTest.getClass();

        Field testFinalThree = reflectTestClass.getDeclaredField("testFinalThree");
        testFinalThree.setAccessible(true);
        //输出code
        System.out.println(reflectTest.getTestFinalThree());
        testFinalThree.set(reflectTest, "code is worse than dance");
        //输出code
        System.out.println(reflectTest.showTestFinalThree());
    }














}
