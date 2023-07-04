package client;

import function.CalculatorFunction;

public class CalculatorDevideFunction implements CalculatorFunction {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() / b.intValue();
    }
}
