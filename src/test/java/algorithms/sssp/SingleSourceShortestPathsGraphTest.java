package algorithms.sssp;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SingleSourceShortestPathsGraphTest {

    static SingleSourceShortestPathsGraph graph = new SingleSourceShortestPathsGraph();

    @BeforeAll
    static void init() {
        graph.add("A", "B", 5);
        graph.add("A", "C", 7);
        graph.add("A", "G", 2);
        graph.add("B", "D", 9);
        graph.add("B", "G", 3);
        graph.add("D", "F", 4);
        graph.add("G", "F", 6);
        graph.add("E", "F", 5);
        graph.add("E", "G", 4);
        graph.add("C", "E", 8);

        assertEquals(7, graph.getVertexes().size());
    }

    @Test
    void should_get_shortest_distance_for_G() {
        Map<String, Integer> shortestDistance = graph.getShortestDistance("G");
        assertEquals(2, shortestDistance.get("A"));
        assertEquals(3, shortestDistance.get("B"));
        assertEquals(4, shortestDistance.get("E"));
        assertEquals(6, shortestDistance.get("F"));
        assertEquals(9, shortestDistance.get("C"));
        assertEquals(10, shortestDistance.get("D"));
    }

}
