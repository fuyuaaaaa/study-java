package java.top.fuyuaaa.study_java.Pattern.FactoryPattern;

/**
 * @author: fuyuaaaaa
 * @description: shape的工厂类
 * @program: StudyDemo
 * @creat: 2018-07-27 09:58
 **/
public class ShapeFactory {

    public static Object getClass(Class<? extends Shape> clazz) {
        Object obj = null;

        try {
            obj = Class.forName(clazz.getName()).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }
}
