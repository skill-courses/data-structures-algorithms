package datastructures.stack;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcstraTest {

    @Test
    void should_cal_simple_expression() {
        Calcstra calcstra = new Calcstra(3);
        int result = calcstra.cal("1+1");
        assertEquals(2, result);
    }

    @Test
    void should_cal_normal_expression() {
        Calcstra calcstra = new Calcstra(7);
        int result = calcstra.cal("(3+4)*5-6");
        assertEquals(29, result);
    }

    @Test
    void should_cal_complex_expression() {
        Calcstra calcstra = new Calcstra(11);
        int result = calcstra.cal("4*5-8+60+8/2");
        assertEquals(76, result);
    }

    @Test
    void should_can_parse_simple_infix_express_to_postfix_expression() {
        final List<String> result = Calcstra.infixToPostfix("2+3");
        assertEquals(List.of("2", "3", "+"), result);
    }

    @Test
    void should_can_parse_complex_infix_express_to_postfix_expression() {
        final List<String> postfixExpression = Calcstra.infixToPostfix("4*5-8+60+8/2");

        List<String> expression = List.of("4", "5", "*", "8", "-", "60", "+", "8", "2", "/", "+");
        assertEquals(expression, postfixExpression);
    }

    @Test
    void should_can_parse_complex_with_brackets_infix_express_to_postfix_expression() {
        final List<String> postfixExpression = Calcstra.infixToPostfix("2*((1+2)/6+3)-5");

        List<String> expression = List.of("2", "1", "2", "+", "6", "/", "3", "+", "*", "5", "-");
        assertEquals(expression, postfixExpression);
    }

    @Test
    void should_can_cal_complex_with_brackets_expression() {
        Calcstra calcstra = new Calcstra(15);
        int result = calcstra.cal("2*((1+2)/6+3)-5");
        assertEquals(1, result);
    }

}
