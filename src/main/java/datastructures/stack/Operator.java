package datastructures.stack;

import java.util.Arrays;

public enum Operator {
    ADD("+", 0, (num1, num2) -> num1 + num2),
    SUBTRACT("-", 0, (num1, num2) -> num1 - num2),
    MULTIPLY("*", 1, (num1, num2) -> num1 * num2),
    DIVIDE("/", 1, (num1, num2) -> num1 / num2);

    private final String symbol;
    private final int priority;
    private final CalculatorOperation operation;

    Operator(String symbol, int priority, CalculatorOperation operation) {
        this.symbol = symbol;
        this.priority = priority;
        this.operation = operation;
    }

    public int getPriority() {
        return priority;
    }

    public double calculate(int num1, int num2) {
        return operation.cal(num1, num2);
    }

    public static Operator fromSymbol(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(op -> op.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid symbol: " + symbol));
    }

    public static boolean isOperator(String symbol) {
        return Arrays.stream(Operator.values()).anyMatch(operator -> operator.symbol.equals(symbol));
    }
}
