package top.fuyuaaa.study.designpattern.abstractfactory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 09:51
 */
@SuppressWarnings("unused")
public abstract class AbstractProductA {
    public void commonMethod(){
        //公共方法
    }

    /**
     * 自定义方法
     */
    public abstract void customizeMethod();
}
