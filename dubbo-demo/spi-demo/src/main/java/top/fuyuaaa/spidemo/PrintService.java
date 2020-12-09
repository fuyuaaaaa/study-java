package top.fuyuaaa.spidemo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

/**
 * @author : fuyuaaa
 * @date : 2020-09-08 17:07
 */
@SPI("chinese")
public interface PrintService {
    void print();

    @Adaptive("language")
    void print(URL url);
}
