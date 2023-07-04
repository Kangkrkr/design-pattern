package client;

import function.CalculatorFunction;

public class CalculatorMultiplyFunction implements CalculatorFunction {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() * b.intValue();
    }
}
