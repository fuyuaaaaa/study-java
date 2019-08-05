package top.fuyuaaa.study.designpattern.factory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 10:08
 */
@SuppressWarnings("unused")
public class Clothes extends AbstractProduct {

    @Override
    public void customizeMethod() {
        System.out.println("我是衣服");
    }
}
