package top.fuyuaaa.study.designpattern.factory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 10:21
 */
public class FactoryPattern {

    public static void main(String[] args) {
        AbstractFactory factory = new Factory();
        AbstractProduct shoes = factory.create(Shoes.class);
        shoes.customizeMethod();
        AbstractProduct clothes = factory.create(Clothes.class);
        clothes.customizeMethod();
    }
}
