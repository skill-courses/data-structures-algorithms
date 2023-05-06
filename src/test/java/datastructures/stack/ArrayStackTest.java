package datastructures.stack;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayStackTest {

    @Test
    void should_init_array_stack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);

        boolean isEmpty = arrayStack.isEmpty();
        assertTrue(isEmpty);
        int size = arrayStack.size();
        assertEquals(0, size);
    }

    @Test
    void should_push_element_to_stack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);

        boolean isEmpty = arrayStack.isEmpty();
        assertTrue(isEmpty);

        arrayStack.push(1);
        int size = arrayStack.size();
        assertEquals(1, size);
    }

    @Test
    void should_throw_exception_when_stack_is_full() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        arrayStack.push(4);
        arrayStack.push(5);

        int size = arrayStack.size();
        assertEquals(5, size);
        boolean isFull = arrayStack.isFull();
        assertTrue(isFull);

        assertThrows(StackOverflowError.class, () -> arrayStack.push(6));
    }

    @Test
    void should_pop_element_after_push_elements() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);

        int size = arrayStack.size();
        assertEquals(1, size);

        int value = arrayStack.pop();
        assertEquals(1, value);
        assertTrue(arrayStack.isEmpty());
    }

    @Test
    void should_throw_exception_when_pop_element_in_empty_stack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);

        int size = arrayStack.size();
        assertEquals(1, size);

        int value = arrayStack.pop();
        assertEquals(1, value);
        assertTrue(arrayStack.isEmpty());

        assertThrows(NoSuchElementException.class, arrayStack::pop);
    }

    @Test
    void should_push_and_pop_multi_elements_in_stack() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);

        final Integer element1 = arrayStack.pop();
        final Integer element2 = arrayStack.pop();
        final Integer element3 = arrayStack.pop();

        assertEquals(3, element1);
        assertEquals(2, element2);
        assertEquals(1, element3);

        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.pop();
        assertEquals(1, arrayStack.size());
    }

    @Test
    void should_peek_top_element() {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(5);
        arrayStack.push(1);
        arrayStack.push(2);
        arrayStack.push(3);
        assertEquals(3, arrayStack.peek());
        assertEquals(3, arrayStack.size());
    }
}
