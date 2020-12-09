package top.fuyuaaa.spidemo.javaspi;

import com.alibaba.dubbo.common.URL;
import top.fuyuaaa.spidemo.PrintService;

/**
 * @author : fuyuaaa
 * @date : 2020-09-08 17:07
 */
public class EnglishPrintServiceImpl implements PrintService {
    @Override
    public void print() {
        System.out.println("English");
    }
    @Override
    public void print(URL url) {
        System.out.println("English URL");
    }

}
