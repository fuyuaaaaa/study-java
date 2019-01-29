package top.fuyuaaa.study.Pattern.demo.number1;

import lombok.Data;

/**
 * @author: fuyuaaaaa
 * @creat: 2019-01-08 22:39
 */
@Data
public class Operation {
    private double numberA = 0;
    private double numberB = 0;

    public double getResult(double numberA, double numberB) {
        double result = 0;
        return result;
    }
}

class OperationAdd extends Operation {
    @Override
    public double getResult(double numberA, double numberB) {
        double result = 0;
        result = numberA + numberB;
        return result;
    }
}

class OperationSub extends Operation {
    @Override
    public double getResult(double numberA, double numberB) {
        double result = 0;
        result = numberA - numberB;
        return result;
    }
}

class OperationMul extends Operation {
    @Override
    public double getResult(double numberA, double numberB) {
        double result = 0;
        result = numberA * numberB;
        return result;
    }
}

class OperationDiv extends Operation {
    @Override
    public double getResult(double numberA, double numberB) {
        double result = 0;
        try {
            result = numberA / numberB;
        } catch (Exception e) {
            System.out.println("除数不能为0！");
        }
        return result;
    }
}
