package datastructures.stack;

import java.util.Arrays;
import java.util.Optional;

public enum Operator {
    ADD("+", 1, Optional.of(Integer::sum)),
    SUBTRACT("-", 1, Optional.of((num1, num2) -> num2 - num1)),
    MULTIPLY("*", 2, Optional.of((num1, num2) -> num1 * num2)),
    DIVIDE("/", 2, Optional.of((num1, num2) -> num2 / num1)),
    LEFT_BRACKET("(", 0, Optional.empty()),
    RIGHT_BRACKET(")", 0, Optional.empty());

    private final String symbol;
    private final int priority;
    private final Optional<CalculatorOperation> operation;

    Operator(String symbol, int priority, Optional<CalculatorOperation> operation) {
        this.symbol = symbol;
        this.priority = priority;
        this.operation = operation;
    }

    public String getSymbol() {
        return symbol;
    }

    public int calculate(int num1, int num2) {
        return this.operation.map(operate -> operate.cal(num1, num2)).orElseThrow(RuntimeException::new);
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

    public boolean lessThanPriority(Operator operator) {
        return this.priority <= operator.priority;
    }


    public boolean equals(String symbol) {
        return this.symbol.equals(symbol);
    }
}
