package datastructures.linkedlist;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class JosephCircleTest {

    @Test
    void should_init_joseph_circle() {
        JosephCircle josephCircle = new JosephCircle(10);

        assertEquals(10, josephCircle.size());
        final List<BoyNode> nodes = josephCircle.getBoys();
        assertEquals(10, nodes.size());
        assertEquals(1, nodes.get(0).getNo());
        assertEquals(10, nodes.get(9).getNo());
    }

    @Test
    void should_init_only_one_boy_joseph_circle() {
        JosephCircle josephCircle = new JosephCircle(1);

        assertEquals(1, josephCircle.size());
        final List<BoyNode> nodes = josephCircle.getBoys();
        assertEquals(1, nodes.size());
        assertEquals(1, nodes.get(0).getNo());
        assertEquals(1, nodes.get(0).getNext().getNo());
    }

    @Test
    void should_init_joseph_circle_failed_when_boy_count_is_0() {
        assertThrows(RuntimeException.class, () -> {
            new JosephCircle(0);
        });
    }

    @Test
    void should_find_last_boy() {
        JosephCircle josephCircle = new JosephCircle(5);
        BoyNode node = josephCircle.lastBoy(2);
        assertEquals(1, josephCircle.size());
        assertEquals(3, node.getNo());
    }

    @Test
    void should_throw_exception_when_step_is_less_than_2() {
        JosephCircle josephCircle = new JosephCircle(5);
        assertThrows(RuntimeException.class, () -> {
            josephCircle.lastBoy(1);
        });
    }

}
