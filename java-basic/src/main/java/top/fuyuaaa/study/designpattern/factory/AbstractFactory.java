package top.fuyuaaa.study.designpattern.factory;

/**
 * @description : 
 * @author : fuyuaaa
 * @create : 2019-08-05 10:11
 */
@SuppressWarnings("unused")
public abstract class AbstractFactory {

    /**
     * 创建产品
     * @param clazz 产品类
     * @param <T> 产品
     * @return 产品
     */
    public abstract <T extends AbstractProduct> T create(Class<T> clazz);
}
