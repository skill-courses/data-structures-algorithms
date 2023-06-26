package algorithms.spg;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ShortestPathsGraphTest {

    static ShortestPathsGraph graph = new ShortestPathsGraph();

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
    void should_get_shortest_distance_for_G_with_Dijkstra() {
        Map<String, Integer> shortestDistance = graph.getShortestDistance("G");
        assertEquals(2, shortestDistance.get("A"));
        assertEquals(3, shortestDistance.get("B"));
        assertEquals(4, shortestDistance.get("E"));
        assertEquals(6, shortestDistance.get("F"));
        assertEquals(9, shortestDistance.get("C"));
        assertEquals(10, shortestDistance.get("D"));
    }


    @Test
    void should_get_shortest_distance_for_all_vertexes_with_Floyed() {
        Map<String, Map<String, Integer>> shortestDistance = graph.getShortestDistance();
        assertEquals(7, shortestDistance.size());

        assertEquals(2, shortestDistance.get("G").get("A"));
        assertEquals(3, shortestDistance.get("G").get("B"));
        assertEquals(4, shortestDistance.get("G").get("E"));
        assertEquals(6, shortestDistance.get("G").get("F"));
        assertEquals(9, shortestDistance.get("G").get("C"));
        assertEquals(10, shortestDistance.get("G").get("D"));

        assertEquals(7, shortestDistance.get("A").get("C"));
        assertEquals(5, shortestDistance.get("A").get("B"));
        assertEquals(6, shortestDistance.get("A").get("E"));
        assertEquals(8, shortestDistance.get("A").get("F"));
        assertEquals(2, shortestDistance.get("A").get("G"));
        assertEquals(12, shortestDistance.get("A").get("D"));
    }

}
