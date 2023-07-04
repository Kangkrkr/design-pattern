package client;

import strategy.CalculatorStrategy;

public class CalculatorMinusStrategy implements CalculatorStrategy {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() - b.intValue();
    }
}
