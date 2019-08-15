package top.fuyuaaa.study.designpattern.decorator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 10:13
 */
public class DecoratorPatternClient {

    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        component = new ConcreteDecorator1(component);
        component = new ConcreteDecorator2(component);
        component.operate();
    }
}
