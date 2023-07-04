package client;

import strategy.CalculatorStrategy;

public class CalculatorMultiplyStrategy implements CalculatorStrategy {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() * b.intValue();
    }
}
