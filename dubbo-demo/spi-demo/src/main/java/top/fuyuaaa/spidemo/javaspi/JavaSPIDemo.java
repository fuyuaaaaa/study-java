package top.fuyuaaa.spidemo.javaspi;

import top.fuyuaaa.spidemo.PrintService;

import java.util.ServiceLoader;

/**
 * @author : fuyuaaa
 * @date : 2020-09-08 17:08
 */
public class JavaSPIDemo {
    public static void main(String[] args) {
        ServiceLoader<PrintService> printServices = ServiceLoader.load(PrintService.class);
        printServices.forEach(PrintService::print);
    }
}
