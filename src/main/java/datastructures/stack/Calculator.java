package datastructures.stack;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Calculator {
    private static final Pattern NUMBER_OPERATION_MATCH = Pattern.compile("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)|(?=\\()|(?<=\\))(?=\\D)");
    protected final ArrayStack<Integer> numberStack;
    private final ArrayStack<Operator> operationStack;

    public Calculator(int length) {
        this.numberStack = new ArrayStack<>(length);
        this.operationStack = new ArrayStack<>(length);
    }

    public int cal(String expression) {
        parseToStack(expression);

        while (!operationStack.isEmpty()) {
            calTwoNumbers(operationStack.pop());
        }
        return numberStack.pop();
    }

    private void parseToStack(String expression) {
        parseExpression(expression).forEach((String item) -> {
            if (Operator.isOperator(item)) {
                Operator operator = Operator.fromSymbol(item);
                if (!operationStack.isEmpty() && operator.lessThanPriority(operationStack.peek())) {
                    calTwoNumbers(operationStack.pop());
                }
                operationStack.push(operator);
            } else {
                numberStack.push(Integer.valueOf(item));
            }
        });
    }

    protected void calTwoNumbers(Operator operator) {
        int num1 = numberStack.pop();
        int num2 = numberStack.pop();
        int result = operator.calculate(num1, num2);
        numberStack.push(result);
    }

    public static List<String> parseExpression(String expression) {
        return NUMBER_OPERATION_MATCH.splitAsStream(expression).collect(Collectors.toList());
    }
}
