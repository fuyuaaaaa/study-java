package top.fuyuaaa.study.designpattern.factory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 10:05
 */
@SuppressWarnings("unused")
public abstract class AbstractProduct {

    /**
     * 公共方法
     */
    public void commonMethod() {
        System.out.println("我是一个产品");
    }

    /**
     * 自定义方法
     */
    public abstract void customizeMethod();
}
