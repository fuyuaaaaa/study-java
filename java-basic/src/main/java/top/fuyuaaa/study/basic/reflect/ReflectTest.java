package top.fuyuaaa.study.basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Test;

/**
 * 反射 
 * @author : fuyuaaa
 * @date : 2020-02-11 14:49
 */
@Getter
@Setter
public class ReflectTest {


    String name = "fuyu";
    int age = 23;

    public ReflectTest() {
    }

    @Test
    public void testGetClassInfo() throws Exception {
        System.out.println(ReflectTest.class);
        System.out.println(Class.forName("top.fuyuaaa.study.basic.reflect.ReflectTest"));

        Field[] fields = ReflectTest.class.getDeclaredFields();
        for (Field field : fields){
            System.out.println(field);
        }
        Method[] methods = ReflectTest.class.getMethods();
        for (Method method: methods) {
            System.out.println(method);
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.println(parameter);
            }
        }
    }
}
