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
        assertEquals(1, Operator.SUBTRACT.calculate(1, 2));
        assertEquals(6, Operator.MULTIPLY.calculate(2, 3));
        assertEquals(2, Operator.DIVIDE.calculate(2, 4));
    }

    @Test
    void should_throw_exception_when_calculate_parameter_has_bracket() {
        assertThrows(RuntimeException.class, () -> Operator.LEFT_BRACKET.calculate(1, 2));
        assertThrows(RuntimeException.class, () -> Operator.RIGHT_BRACKET.calculate(1, 2));
    }

        @Test
    void should_compare_symbol_priority() {
        assertTrue(Operator.ADD.lessThanPriority(Operator.SUBTRACT));
        assertTrue(Operator.ADD.lessThanPriority(Operator.MULTIPLY));
        assertTrue(Operator.MULTIPLY.lessThanPriority(Operator.DIVIDE));
        assertTrue(Operator.LEFT_BRACKET.lessThanPriority(Operator.ADD));
        assertTrue(Operator.LEFT_BRACKET.lessThanPriority(Operator.RIGHT_BRACKET));
        assertFalse(Operator.MULTIPLY.lessThanPriority(Operator.ADD));
    }

}
