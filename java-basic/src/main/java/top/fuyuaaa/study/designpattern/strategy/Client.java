package top.fuyuaaa.study.designpattern.strategy;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-22 19:51
 */
public class Client {

    public static void main(String[] args) {
        Strategy strategy = new ConcreteStrategy1();
        Context context  = new Context(strategy);
        context.doAnything();
    }
}
