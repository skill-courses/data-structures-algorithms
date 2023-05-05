package datastructures.stack;

public class Calculator {
    private ArrayStack<Integer> numberStack;
    private ArrayStack<String> operationStack;

    public Calculator(int length) {
        this.numberStack = new ArrayStack<>(length);
        this.operationStack = new ArrayStack<>(length);
    }

    public int cal(String expression) {
        return 0;
    }
}
