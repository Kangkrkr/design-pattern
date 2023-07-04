package client;

import function.CalculatorFunction;
import function.CalculatorMinusFunction;
import function.CalculatorPlusFunction;

public class CalculatorFactory<T extends function.CalculatorFunction> {

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

    public <T extends function.CalculatorFunction> function.CalculatorFunction createCalculatorFunction(char operator) throws Exception {
        CalculatorFunction function = null;

        switch (operator) {
            case '+' :
                function = new CalculatorPlusFunction();
                break;
            case '-' :
                function = new CalculatorMinusFunction();
                break;
            default:
                throw new Exception("Unsupported operation");
        }

        return function;
    }
}
