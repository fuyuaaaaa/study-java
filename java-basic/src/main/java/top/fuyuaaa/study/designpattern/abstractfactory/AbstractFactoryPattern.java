package top.fuyuaaa.study.designpattern.abstractfactory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 09:59
 */
public class AbstractFactoryPattern {

    public static void main(String[] args) {
        AbstractCreator creator1 = new Creator1();
        AbstractProductA a1 = creator1.createA();
        a1.customizeMethod();
        AbstractProductB b1 = creator1.createB();
        b1.customizedMethod();

        AbstractCreator creator2 = new Creator2();
        AbstractProductA a2 = creator2.createA();
        a2.customizeMethod();
        AbstractProductB b2 = creator2.createB();
        b2.customizedMethod();
    }
}
