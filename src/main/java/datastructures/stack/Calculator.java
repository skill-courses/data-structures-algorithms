package datastructures.stack;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    private static final Pattern NUMBER_OPERATION_MATCH = Pattern.compile("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    private final ArrayStack<Integer> numberStack;
    private final ArrayStack<String> operationStack;

    public Calculator(int length) {
        this.numberStack = new ArrayStack<>(length);
        this.operationStack = new ArrayStack<>(length);
    }

    public int cal(String expression) {
        parseToStack(expression);

        while (!operationStack.isEmpty()) {
            calTwoNumbers();
        }
        return numberStack.pop();
    }

    private void parseToStack(String expression) {
        parseExpression(expression).forEach((String item) -> {
            if (Operator.isOperator(item)) {
                if (!operationStack.isEmpty() && Operator.comparePriority (item, operationStack.peek())) {
                    calTwoNumbers();
                }
                operationStack.push(item);
            } else {
                numberStack.push(Integer.valueOf(item));
            }
        });
    }

    private void calTwoNumbers() {
        int num1 = numberStack.pop();
        int num2 = numberStack.pop();
        Operator operator = Operator.fromSymbol(operationStack.pop());
        int result = operator.calculate(num1, num2);
        numberStack.push(result);
    }

    private static List<String> parseExpression(String expression) {
        return NUMBER_OPERATION_MATCH.splitAsStream(expression).collect(Collectors.toList());
    }
}
