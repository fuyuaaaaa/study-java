package top.fuyuaaa.study.designpattern.templatemethod;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 14:37
 */
public class TemPlateMethodPattern {

    public static void main(String[] args) {
        AbstractClass classA = new ConcreteClassA();
        classA.templateMethod();
        AbstractClass classB = new ConcreteClassB();
        classB.templateMethod();
    }
}
