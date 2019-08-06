package top.fuyuaaa.study.designpattern.abstractfactory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 09:59
 */
public class Creator2 extends AbstractCreator {

    @Override
    public AbstractProductA createA() {
        return new ProductA2();
    }

    @Override
    public AbstractProductB createB() {
        return new ProductB2();
    }
}
