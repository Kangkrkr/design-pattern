package strategy;

@FunctionalInterface
public interface CalculatorStrategy {
    public Number process(Number a, Number b);
}
