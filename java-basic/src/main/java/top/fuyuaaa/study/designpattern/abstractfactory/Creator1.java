package top.fuyuaaa.study.designpattern.abstractfactory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 09:58
 */
public class Creator1 extends AbstractCreator {

    @Override
    public AbstractProductA createA() {
        return new ProductA1();
    }

    @Override
    public AbstractProductB createB() {
        return new ProductB1();
    }
}
