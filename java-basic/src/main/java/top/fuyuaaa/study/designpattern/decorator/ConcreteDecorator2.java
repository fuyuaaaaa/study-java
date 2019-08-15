package top.fuyuaaa.study.designpattern.decorator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 10:11
 */
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(Component component) {
        super(component);
    }

    public void method2() {
        System.out.println("method2修饰");
    }

    @Override
    public void operate() {
        super.operate();
        this.method2();
    }
}
