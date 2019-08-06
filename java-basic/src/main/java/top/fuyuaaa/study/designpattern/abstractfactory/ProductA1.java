package top.fuyuaaa.study.designpattern.abstractfactory;


/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 09:53
 */
@SuppressWarnings("unused")
public class ProductA1 extends AbstractProductA {

    /**
     * 自定义方法
     */
    @Override
    public void customizeMethod() {
        System.out.println("我是产品A1");
    }
}
