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

    @Test
    void should_init_calculator_and_cal_three_numbers_with_same_expression() {
        Calculator calculator = new Calculator(3);
        int result = calculator.cal("1+1+1");
        assertEquals(3, result);
    }

    @Test
    void should_init_calculator_and_cal_three_numbers_with_not_same_expression() {
        Calculator calculator = new Calculator(3);
        int result = calculator.cal("1+1-1");
        assertEquals(1, result);
    }

    @Test
    void should_init_calculator_and_cal_three_numbers_with_not_same_or_priority_expression() {
        Calculator calculator = new Calculator(4);
        int result = calculator.cal("2+2*3-1");
        assertEquals(7, result);

        int result2 = calculator.cal("2+2*3-6/2");
        assertEquals(5, result2);
    }

    @Test
    void should_init_calculator_and_cal_multi_numbers_and_expression() {
        Calculator calculator = new Calculator(10);
        int result = calculator.cal("2+2*3*2/2*3-6/2*2");
        assertEquals(14, result);
    }
}
