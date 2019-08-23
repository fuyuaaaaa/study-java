package top.fuyuaaa.study.designpattern.strategy;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-22 19:49
 */
public class Context {
    private Strategy strategy = null;
    public Context (Strategy strategy) {
        this.strategy = strategy;
    }
    public void doAnything() {
        this.strategy.doSomething();
    }
}
