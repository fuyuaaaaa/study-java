package top.fuyuaaa.spidemo;

import com.alibaba.dubbo.common.URL;

/**
 * @author : fuyuaaa
 * @date : 2020-09-09 10:39
 */
public class PrintServiceWrapper implements PrintService {
    private final PrintService printService;

    public PrintServiceWrapper(PrintService printService) {
        this.printService = printService;
    }

    @Override
    public void print() {
        System.out.println("做一下前置工作");

        printService.print();

        System.out.println("做一下后置工作");
    }

    @Override
    public void print(URL url) {
        System.out.println("做一下前置工作");

        printService.print(url);

        System.out.println("做一下后置工作");
    }
}
