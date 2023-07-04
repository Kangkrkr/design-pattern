package function;

public class CalculatorPlusFunction implements CalculatorFunction {
    @Override
    public Number process(Number a, Number b) {
        return a.intValue() + b.intValue();
    }
}
