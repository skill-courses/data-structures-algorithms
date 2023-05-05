package datastructures.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    void should_init_calculator_and_cal_single_expression() {
        Calculator calculator = new Calculator(2);
        int result = calculator.cal("1+1");
        assertEquals(2, result);
    }

}
