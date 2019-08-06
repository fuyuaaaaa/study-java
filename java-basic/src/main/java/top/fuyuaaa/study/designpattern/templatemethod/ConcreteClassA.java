package top.fuyuaaa.study.designpattern.templatemethod;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 14:34
 */
public class ConcreteClassA extends AbstractClass {

    @Override
    protected void doSomeThing() {
        System.out.println("ConcreteClassA-doSomeThing");
    }

    @Override
    protected void doAnyThing() {
        System.out.println("ConcreteClassA-doAnyThing");
    }
}
