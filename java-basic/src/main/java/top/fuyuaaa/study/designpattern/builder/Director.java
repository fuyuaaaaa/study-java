package top.fuyuaaa.study.designpattern.builder;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-07 15:02
 */
public class Director {
    public Product create(Builder builder) {
        builder.bulidA();
        builder.bulidB();
        builder.bulidC();
        builder.bulidD();
        return builder.getProduct();
    }
}
