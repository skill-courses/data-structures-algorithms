package algorithms.prim;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KruskalGraphTest {

    @Test
    void should_add_path_for_kurskal_graph() {
        KruskalGraph graph = new KruskalGraph();
        graph.add(Path.of("A", "B"));
        graph.add(Path.of("A", "C"));
        graph.add(Path.of("D", "B"));

        Set<Path> paths = graph.getPaths();
        assertEquals(3, paths.size());
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B", "C", "D"), vertexes);
    }

    @Test
    void should_not_add_duplicate_path_for_kurskal_graph() {
        KruskalGraph graph = new KruskalGraph();
        graph.add(Path.of("A", "B"));
        graph.add(Path.of("A", "C"));
        graph.add(Path.of("C", "A"));

        Set<Path> paths = graph.getPaths();
        assertEquals(2, paths.size());
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B", "C"), vertexes);
    }

    @Test
    void should_not_add_circle_path_for_kurskal_graph() {
        KruskalGraph graph = new KruskalGraph();
        graph.add(Path.of("A", "B"));
        graph.add(Path.of("A", "C"));
        graph.add(Path.of("C", "B"));

        Set<Path> paths = graph.getPaths();
        assertEquals(2, paths.size());
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B", "C"), vertexes);
    }

    @Test
    void should_not_add_circle_path_for_complex_kurskal_graph() {
        KruskalGraph graph = new KruskalGraph();
        graph.add(Path.of("A", "B"));
        graph.add(Path.of("B", "D"));
        graph.add(Path.of("C", "D"));
        graph.add(Path.of("E", "B"));
        graph.add(Path.of("E", "D"));

        Set<Path> paths = graph.getPaths();
        assertEquals(4, paths.size());
        Set<String> vertexes = graph.getVertexes();
        assertEquals(Set.of("A", "B", "C", "D", "E"), vertexes);
    }
}
