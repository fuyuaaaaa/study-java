package top.fuyuaaa.study.designpattern.templatemethod;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 14:33
 */
public abstract class AbstractClass {
    protected abstract void doSomeThing();
    protected abstract void doAnyThing();
    public void templateMethod(){
        this.doSomeThing();
        this.doAnyThing();
    }
}
