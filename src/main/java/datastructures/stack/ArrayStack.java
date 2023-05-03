package datastructures.stack;

import java.util.NoSuchElementException;

public class ArrayStack<T> {

    private final int maxSize;
    private final T[] arr;
    private int top = -1;

    @SuppressWarnings("unchecked")
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.arr = (T[]) new Object[maxSize];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    public void push(T element) {
        if (isFull()) {
            throw new StackOverflowError();
        }

        top++;
        arr[top] = element;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        final T element = arr[top];
        top--;
        return element;
    }
}
