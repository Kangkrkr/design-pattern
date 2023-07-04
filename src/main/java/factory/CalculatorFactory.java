package factory;

import client.Calculator;
import strategy.*;

public class CalculatorFactory<T extends CalculatorStrategy> {

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

    public <T extends CalculatorStrategy> CalculatorStrategy createCalculatorFunction(char operator) throws Exception {
        CalculatorStrategy function = null;

        switch (operator) {
            case '+' :
                function = new CalculatorPlusStrategy();
                break;
            case '-' :
                function = new CalculatorMinusStrategy();
                break;
            case '*' :
                function = new CalculatorMultiplyStrategy();
                break;
            case '/' :
                function = new CalculatorDevideStrategy();
                break;
            default:
                throw new Exception("Unsupported operation");
        }

        return function;
    }
}
