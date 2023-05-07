package datastructures.stack;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalcstraTest {

    @Test
    void should_cal_simple_expression() {
        final List<String> expression = List.of("1", "1", "+");

        Calcstra calcstra = new Calcstra(expression.size());
        int result = calcstra.cal(expression);
        assertEquals(2, result);
    }

    @Test
    void should_cal_normal_expression() {
        final List<String> expression = List.of("3", "4", "+", "5", "*", "6", "-");

        Calcstra calcstra = new Calcstra(expression.size());
        int result = calcstra.cal(expression);
        assertEquals(29, result);
    }

    @Test
    void should_cal_complex_expression() {
        final List<String> expression = List.of("4", "5", "*", "8", "-", "60", "+", "8", "2", "/", "+");

        Calcstra calcstra = new Calcstra(expression.size());
        int result = calcstra.cal(expression);
        assertEquals(76, result);
    }

}
