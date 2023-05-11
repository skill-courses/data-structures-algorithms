package datastructures.stack;

import java.util.ArrayList;
import java.util.List;

public class Calcstra extends Calculator {

    public Calcstra(int size) {
        super(size);
    }

    @Override
    public int cal(String expression) {
        parseToNumbers(expression);

        return this.numberStack.pop();
    }

    private void parseToNumbers(String expression) {
        infixToPostfix(expression).forEach((String item) -> {
            if (Operator.isOperator(item)) {
                this.calTwoNumbers(Operator.fromSymbol(item));
            } else {
                this.numberStack.push(Integer.valueOf(item));
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
