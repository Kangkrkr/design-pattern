package client;

import factory.CalculatorFactory;
import strategy.CalculatorStrategy;
import strategy.CalculatorMinusStrategy;
import strategy.CalculatorPlusStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @DisplayName("Calculator 클래스 싱글턴 테스트")
    @Test
    public void calculator_equals_test() {
        Object o1 = new Object();
        Object o2 = new Object();
        Assertions.assertNotEquals(o1, o2);

        Calculator calculator1 = Calculator.getInstance();
        Calculator calculator2 = Calculator.getInstance();
        Assertions.assertEquals(calculator1, calculator2);
    }

    @DisplayName("CalculatorPlusFunction 테스트")
    @Test
    public void calculator_plus_function_test() {
        CalculatorStrategy plusFunction = new CalculatorPlusStrategy();
        Number result = plusFunction.process(1, 2);

        Assertions.assertEquals(3, result);
    }

    @DisplayName("CalculatorMinusFunction 테스트")
    @Test
    public void calculator_minus_function_test() {
        CalculatorStrategy minusFunction = new CalculatorMinusStrategy();
        Number result = minusFunction.process(1, 2);

        Assertions.assertEquals(-1, result);
    }

    @DisplayName("CalculatorFactory 테스트")
    @Test
    public void calculator_factory_test() throws Exception {
        factory.CalculatorFactory factory = CalculatorFactory.getInstance();

        CalculatorStrategy calculatorStrategy1 = factory.createCalculatorFunction('+');
        Number result1 = calculatorStrategy1.process(1, 2);
        Assertions.assertEquals(3, result1);

        CalculatorStrategy calculatorStrategy2 = factory.createCalculatorFunction('-');
        Number result2 = calculatorStrategy2.process(1, 2);
        Assertions.assertEquals(-1, result2);
    }

    @DisplayName("CalculatorFactory 미지원 연산 예외 테스트")
    @Test
    public void calculator_factory_unsupported_operation_exception_test(){
        factory.CalculatorFactory factory = CalculatorFactory.getInstance();

        Exception exception = assertThrows(Exception.class, () -> {
            factory.createCalculatorFunction('?');
        });

        String expectedMessage = "Unsupported operation";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}