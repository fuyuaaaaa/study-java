package top.fuyuaaa.study.designpattern.builder;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-07 15:09
 */
public class BuilderPattern {

    public static void main(String[] args) {
        Director director = new Director();
        Product aProduct = director.create(new ConcreteBuilder());
        System.out.println(aProduct);
    }
}
