package top.fuyuaaa.study.designpattern.decorator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 10:09
 */
public class ConcreteComponent extends Component {

    @Override
    public void operate() {
        System.out.println("do something");
    }
}
