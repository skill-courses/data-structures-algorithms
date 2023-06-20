package datastructures.graph;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphTest {

    @Test
    void should_add_routes_to_graph() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 20);
        graph.addRoute("B", "C", 20);

        int routeSize = graph.getRouteSize();
        assertEquals(2, routeSize);
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B", "C"), vertexes);
    }

    @Test
    void should_remove_duplicate_routes_when_add_route_to_graph() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 20);
        graph.addRoute("A", "B", 20);

        int routeSize = graph.getRouteSize();
        assertEquals(1, routeSize);
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B"), vertexes);
    }

    @Test
    void should_find_routes_by_one_vertex() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 20);
        graph.addRoute("B", "C", 20);
        graph.addRoute("A", "C", 20);
        graph.addRoute("A", "D", 20);
        graph.addRoute("D", "C", 20);

        Set<Route> routesA = graph.getRoutesByVertex("A");
        Set<Route> routesC = graph.getRoutesByVertex("C");
        assertEquals(3, routesA.size());
        assertEquals(0, routesC.size());
    }

    @Test
    void should_find_routes_from_vertex_to_another_vertex_by_dfs() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 10);
        graph.addRoute("B", "E", 13);
        graph.addRoute("A", "C", 15);
        graph.addRoute("C", "E", 5);
        graph.addRoute("A", "D", 12);
        graph.addRoute("D", "E", 20);
        graph.addRoute("D", "C", 3);
        graph.addRoute("B", "C", 2);

        assertEquals(8, graph.getRouteSize());
        List<Set<Route>> paths = graph.getPathsByDFS("A", "E");
        assertEquals(5, paths.size());
        final var expected = Set.of(new Route("A", "B"), new Route("B", "E"));
        assertTrue(paths.contains(expected));
    }

    @Test
    void should_find_routes_from_vertex_to_another_vertex_by_bfs() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 10);
        graph.addRoute("B", "E", 13);
        graph.addRoute("A", "C", 15);
        graph.addRoute("C", "E", 5);
        graph.addRoute("A", "D", 12);
        graph.addRoute("D", "E", 20);
        graph.addRoute("D", "C", 3);
        graph.addRoute("B", "C", 2);

        assertEquals(8, graph.getRouteSize());
        List<Set<Route>> paths = graph.getPathByBFS("A", "E");
        assertEquals(5, paths.size());
        final var expected = Set.of(new Route("A", "B"), new Route("B", "E"));
        assertTrue(paths.contains(expected));
    }

    @Test
    void should_find_min_weight_routes_from_vertex_to_another_vertex_by_dfs() {
        Graph graph = new Graph();
        graph.addRoute("A", "B", 10);
        graph.addRoute("B", "E", 13);
        graph.addRoute("A", "C", 15);
        graph.addRoute("C", "E", 5);
        graph.addRoute("A", "D", 12);
        graph.addRoute("D", "E", 20);
        graph.addRoute("D", "C", 3);
        graph.addRoute("B", "C", 2);

        Set<Route> path = graph.getMinWeightPaths("A", "E");
        final var expected = Set.of(new Route("A", "B"), new Route("B", "C"), new Route("C", "E"));
        assertEquals(expected, path);
    }
}
