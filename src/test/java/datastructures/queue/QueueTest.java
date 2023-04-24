package datastructures.queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class QueueTest {

    @Test
    void should_create_empty_queue() {
        Queue<Integer> queue = new Queue<>(3);
        assertTrue(queue.isEmpty());
    }

    @Test
    void should_add_elements_to_queue() {
        Queue<Integer> queue = new Queue(3);

        queue.addElements(1);
        queue.addElements(2);

        assertEquals(2, queue.size());
    }

    @Test
    void should_add_elements_to_full_to_queue() {
        Queue<Integer> queue = new Queue(5);

        queue.addElements(1);
        queue.addElements(2);
        queue.addElements(3);
        queue.addElements(4);
        queue.addElements(5);

        assertEquals(5, queue.size());
        assertTrue(queue.isFull());
    }

    @Test
    void should_throw_exception_when_queue_is_full() {
        Queue<Integer> queue = new Queue(5);

        queue.addElements(1);
        queue.addElements(2);
        queue.addElements(3);
        queue.addElements(4);
        queue.addElements(5);

        assertThrows(IllegalStateException.class, () -> queue.addElements(6));

        assertEquals(5, queue.size());
        assertTrue(queue.isFull());
    }

    @Test
    void should_remove_element() {
        Queue<Integer> queue = new Queue(5);

        queue.addElements(1);
        queue.addElements(2);
        queue.addElements(3);

        assertEquals(3, queue.size());

        int element = queue.removeElement();
        assertEquals(1, element);
        assertEquals(2, queue.size());
    }

    @Test
    void should_remove_all_elements() {
        Queue<Integer> queue = new Queue(5);

        queue.addElements(1);
        queue.addElements(2);
        queue.addElements(3);

        assertEquals(3, queue.size());

        int element = queue.removeElement();
        assertEquals(1, element);
        assertEquals(2, queue.size());

        int element1 = queue.removeElement();
        assertEquals(2, element1);
        assertEquals(1, queue.size());

        int element2 = queue.removeElement();
        assertEquals(3, element2);
        assertTrue(queue.isEmpty());
    }

    @Test
    void should_add_element_and_remove_elements() {
        Queue<Integer> queue = new Queue(5);

        queue.addElements(1);
        queue.addElements(2);

        queue.removeElement();

        queue.addElements(3);
        queue.addElements(4);

        queue.removeElement();
        queue.removeElement();

        queue.addElements(5);
        queue.addElements(6);
        queue.addElements(7);

        queue.removeElement();
        queue.removeElement();
        queue.removeElement();

        assertEquals(1, queue.size());
    }

    @Test
    void should_throw_exception_when_remove_elements_in_empty_queue() {
        Queue queue = new Queue(3);

        assertThrows(IllegalStateException.class, () -> queue.removeElement());
    }

}
