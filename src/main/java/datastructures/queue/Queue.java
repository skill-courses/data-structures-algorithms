package datastructures.queue;

public class Queue<T> {
    private final T[] arr;
    private int front; // 队头
    private int rear; // 队尾
    private final int capacity; // 队列容量
    private int elementsSize;

    public Queue(int capacity) {
        this.capacity = capacity;
        this.arr = (T[]) new Object[this.capacity];
    }

    public void addElements(T elements) {
        if (this.isFull()) {
            throw new IllegalStateException("The queue is full");
        }

        arr[rear] = elements;
        elementsSize ++;
        this.rear = (this.rear + 1) % this.capacity;
    }

    public int size() {
        return elementsSize;
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean isFull() {
        return elementsSize ==  this.capacity;
    }

    public T removeElement() {
        if (this.isEmpty()) {
            throw new IllegalStateException("The queue is empty");
        }
        T item = arr[front];
        arr[front] = null;
        front = (front + 1) % this.capacity;
        elementsSize --;
        return item;
    }
}
