package algorithms.mst;

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

    @Test
    void should_get_min_weight_for_graph_by_kruskal() {
        PrimGraph graph = new PrimGraph();
        graph.addRoute("A", "B", 12);
        graph.addRoute("A", "F", 16);
        graph.addRoute("A", "G", 14);
        graph.addRoute("B", "C", 10);
        graph.addRoute("B", "F", 7);
        graph.addRoute("F", "C", 6);
        graph.addRoute("F", "G", 9);
        graph.addRoute("F", "E", 2);
        graph.addRoute("E", "G", 8);
        graph.addRoute("D", "E", 4);
        graph.addRoute("D", "C", 3);
        graph.addRoute("E", "C", 5);

        Set<Route> paths = graph.getShortestPathsByKruskal();
        assertEquals(6, paths.size());
        int sum = paths.stream().mapToInt(Route::getWeight).sum();
        assertEquals(36, sum);
    }

}
