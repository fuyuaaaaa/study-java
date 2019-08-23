package top.fuyuaaa.study.designpattern.strategy;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-22 19:47
 */
public class ConcreteStrategy2 implements Strategy {

    @Override
    public void doSomething() {
        System.out.println("具体策略算法二");
    }
}
