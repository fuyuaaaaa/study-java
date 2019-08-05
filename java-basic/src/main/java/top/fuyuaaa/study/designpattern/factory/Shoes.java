package top.fuyuaaa.study.designpattern.factory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 10:10
 */
@SuppressWarnings("unused")
public class Shoes extends AbstractProduct {

    @Override
    public void customizeMethod() {
        System.out.println("我是鞋子");
    }
}
