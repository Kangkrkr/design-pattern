package strategy;

public class CalculatorDevideStrategy implements CalculatorStrategy {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() / b.intValue();
    }
}
