package top.fuyuaaa.study.designpattern.templatemethod;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 14:35
 */
public class ConcreteClassB extends AbstractClass {
    @Override
    protected void doSomeThing() {
        System.out.println("ConcreteClassB-doSomeThing");
    }

    @Override
    protected void doAnyThing() {
        System.out.println("ConcreteClassB-doAnyThing");
    }
}
