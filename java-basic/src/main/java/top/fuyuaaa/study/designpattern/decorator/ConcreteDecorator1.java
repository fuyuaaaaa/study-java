package top.fuyuaaa.study.designpattern.decorator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 10:11
 */
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(Component component) {
        super(component);
    }

    public void method1() {
        System.out.println("method1修饰");
    }

    @Override
    public void operate() {
        this.method1();
        super.operate();
    }
}
