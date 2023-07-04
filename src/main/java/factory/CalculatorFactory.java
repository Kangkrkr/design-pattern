package factory;

import client.Calculator;
import function.*;

public class CalculatorFactory<T extends CalculatorFunction> {

    private static CalculatorFactory calculatorFactory;

    private CalculatorFactory() {}

    public static CalculatorFactory getInstance() {
        if (calculatorFactory == null) {
            synchronized (Calculator.class) {
                if (calculatorFactory == null) {
                    calculatorFactory = new CalculatorFactory();
                }
            }
        }

        return calculatorFactory;
    }

    public <T extends CalculatorFunction> CalculatorFunction createCalculatorFunction(char operator) throws Exception {
        CalculatorFunction function = null;

        switch (operator) {
            case '+' :
                function = new CalculatorPlusFunction();
                break;
            case '-' :
                function = new CalculatorMinusFunction();
                break;
            case '*' :
                function = new CalculatorMultiplyFunction();
                break;
            case '/' :
                function = new CalculatorDevideFunction();
                break;
            default:
                throw new Exception("Unsupported operation");
        }

        return function;
    }
}
