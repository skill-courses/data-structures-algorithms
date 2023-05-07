package datastructures.stack;

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
}
