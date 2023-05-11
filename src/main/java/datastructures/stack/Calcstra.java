package datastructures.stack;

import java.util.ArrayList;
import java.util.List;

public class Calcstra {

    private final ArrayStack<Integer> numbers;

    public Calcstra(int size) {
        numbers = new ArrayStack<>(size);
    }

    public int cal(List<String> expression) {
        parseToNumbers(expression);

        return numbers.pop();
    }

    private void parseToNumbers(List<String> expression) {
        expression.forEach((String item) -> {
            if (Operator.isOperator(item)) {
                int num1 = numbers.pop();
                int num2 = numbers.pop();
                int result = Operator.fromSymbol(item).calculate(num1, num2);
                numbers.push(result);
            } else {
                numbers.push(Integer.valueOf(item));
            }
        });
    }

    public static List<String> infixToPostfix(String expression) {
        final List<String> expressions = Calculator.parseExpression(expression);
        List<String> result = new ArrayList<>();
        ArrayStack<Operator> operators = new ArrayStack<>(expressions.size());

        expressions.forEach((String item) -> {
            if (Operator.isOperator(item)) {
                final Operator operator = Operator.fromSymbol(item);
                if (Operator.RIGHT_BRACKET == operator) {
                    while (!operators.isEmpty() && operators.peek() != Operator.LEFT_BRACKET) {
                        result.add(operators.pop().getSymbol());
                    }
                    if (!operators.isEmpty()) {
                        operators.pop();
                    }
                } else {
                    if (Operator.LEFT_BRACKET != operator) {
                        while (!operators.isEmpty() && operator.lessThanPriority(operators.peek())) {
                            result.add(operators.pop().getSymbol());
                        }
                    }
                    operators.push(operator);
                }
            } else {
                result.add(item);
            }
        });

        while (!operators.isEmpty()) {
            result.add(operators.pop().getSymbol());
        }

        return result;
    }
}
