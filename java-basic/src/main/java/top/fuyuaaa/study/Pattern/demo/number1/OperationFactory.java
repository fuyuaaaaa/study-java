package top.fuyuaaa.study.Pattern.demo.number1;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-08 22:50
 */
public class OperationFactory {

    public static void main(String[] args) {
        Operation operation = createOperate("+");
        System.out.println(operation.getResult(1, 2));
    }

    public static Operation createOperate(String operate) {
        Operation operation = null;
        switch (operate) {
            case "+":
                operation = new OperationAdd();
                break;
            case "-":
                operation = new OperationSub();
                break;
            case "*":
                operation = new OperationMul();
                break;
            case "/":
                operation = new OperationDiv();
                break;
        }
        return operation;
    }
}
