package client;

import strategy.CalculatorStrategy;

public class CalculatorPlusStrategy implements CalculatorStrategy {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() + b.intValue();
    }
}
