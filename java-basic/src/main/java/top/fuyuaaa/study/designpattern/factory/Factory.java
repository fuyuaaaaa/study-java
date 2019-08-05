package top.fuyuaaa.study.designpattern.factory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 10:13
 */
@SuppressWarnings("all")
public class Factory extends AbstractFactory {

    /**
     * 创建产品
     * @param clazz 产品类
     * @return 产品
     */
    @Override
    public <T extends AbstractProduct> T create(Class<T> clazz) {
        AbstractProduct product = null;
        try {
            product = (AbstractProduct) Class.forName(clazz.getName()).newInstance();
        } catch (Exception e) {

        }
        return (T)product;
    }
}
