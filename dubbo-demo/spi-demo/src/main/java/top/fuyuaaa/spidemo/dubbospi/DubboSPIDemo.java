package top.fuyuaaa.spidemo.dubbospi;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import top.fuyuaaa.spidemo.PrintService;

import java.util.Collections;

/**
 * @author : fuyuaaa
 * @date : 2020-09-08 17:29
 */
public class DubboSPIDemo {
    public static void main(String[] args) {
        ExtensionLoader<PrintService> extensionLoader = ExtensionLoader.getExtensionLoader(PrintService.class);
        PrintService defaultPrintService = extensionLoader.getDefaultExtension();
        defaultPrintService.print();

        URL url = new URL("test", "test", 8080, "test", Collections.singletonMap("language", "english"));
        PrintService adaptiveExtension = extensionLoader.getAdaptiveExtension();
        adaptiveExtension.print(url);
    }
}
