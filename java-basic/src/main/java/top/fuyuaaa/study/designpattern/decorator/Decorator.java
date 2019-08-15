package top.fuyuaaa.study.designpattern.decorator;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-15 10:09
 */
public class Decorator extends Component{
    private Component component = null;
    public Decorator(Component component) {
        this.component = component;
    }
    @Override
    public void operate(){
        this.component.operate();
    }
}
