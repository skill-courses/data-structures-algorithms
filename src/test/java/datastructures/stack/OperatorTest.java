package datastructures.stack;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class OperatorTest {

    @Test
    void should_can_get_operator_enum_from_symbol() {
        final Operator add = Operator.fromSymbol("+");
        final Operator sub = Operator.fromSymbol("-");
        final Operator multiply = Operator.fromSymbol("*");
        final Operator divide = Operator.fromSymbol("/");

        assertEquals(Operator.ADD, add);
        assertEquals(Operator.SUBTRACT, sub);
        assertEquals(Operator.MULTIPLY, multiply);
        assertEquals(Operator.DIVIDE, divide);
    }

    @Test
    void should_throw_exception_from_invalid_symbol() {
        assertThrows(IllegalArgumentException.class, () -> Operator.fromSymbol("123"));
    }

    @Test
    void should_determines_whether_it_is_an_operator() {
        assertTrue(Operator.isOperator("+"));
        assertTrue(Operator.isOperator("-"));
        assertTrue(Operator.isOperator("*"));
        assertTrue(Operator.isOperator("/"));
        assertFalse(Operator.isOperator("123"));
    }

    @Test
    void should_calculate_two_number() {
        assertEquals(2, Operator.ADD.calculate(1, 1));
        assertEquals(1, Operator.SUBTRACT.calculate(2, 1));
        assertEquals(6, Operator.MULTIPLY.calculate(2, 3));
        assertEquals(2, Operator.DIVIDE.calculate(4, 2));
    }

}
