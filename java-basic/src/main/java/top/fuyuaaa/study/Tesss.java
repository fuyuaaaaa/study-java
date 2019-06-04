package top.fuyuaaa.study;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-14 13:49
 */
public class Tesss {

    @Test
    void test() throws ParseException {
        String amount1 = "x";
        if ((Object)amount1 instanceof Double) {
            System.out.println(1111);
        }
        double d1 = new DecimalFormat().parse(amount1).doubleValue(); //这里使用的是parse，不是format
        System.out.println(String.valueOf(d1)); //结果是13000.00
    }
}
