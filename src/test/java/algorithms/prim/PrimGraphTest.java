package algorithms.prim;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import datastructures.graph.Route;
import org.junit.jupiter.api.Test;

class PrimGraphTest {

    @Test
    void should_get_min_weight_for_graph() {
        PrimGraph graph = new PrimGraph();
        graph.addRoute("A", "B", 5);
        graph.addRoute("A", "C", 7);
        graph.addRoute("A", "G", 2);
        graph.addRoute("B", "D", 9);
        graph.addRoute("B", "G", 3);
        graph.addRoute("D", "F", 4);
        graph.addRoute("G", "F", 6);
        graph.addRoute("E", "F", 5);
        graph.addRoute("E", "G", 4);
        graph.addRoute("C", "E", 8);

        Set<Route> paths = graph.getShortestPaths();
        assertEquals(6, paths.size());
        int sum = paths.stream().mapToInt(Route::getWeight).sum();
        assertEquals(25, sum);
    }

}
