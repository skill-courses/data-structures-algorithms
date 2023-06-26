package algorithms.sssp;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import algorithms.mst.Path;

public class SingleSourceShortestPathsGraph {
    private final Set<Path> paths = new HashSet<>();
    private final Set<String> vertexes = new HashSet<>();

    public void add(Path path) {
        if (!hasPath(path)) {
            paths.add(path);
            vertexes.addAll(Set.of(path.getLeft(), path.getRight()));
        }
    }

    public void add(String left, String right, int weight) {
        Path path = new Path(left, right, weight);
        this.add(path);
    }

    public boolean hasPath(Path path) {
        return this.paths.stream().anyMatch(path::equals);
    }

    public Set<String> getVertexes() {
        return Set.of(vertexes.toArray(String[]::new));
    }

    public Map<String, Integer> getShortestDistance(String source) {
        Map<String, Integer> shortestDistances = this.vertexes.stream()
                .collect(Collectors.toMap(vertex -> vertex, vertex -> vertex.equals(source) ? 0 : Integer.MAX_VALUE));

        Set<String> visited = new HashSet<>();
        while (visited.size() < vertexes.size()) {
            this.vertexes.stream().filter(vertex -> !visited.contains(vertex))
                    .min(Comparator.comparingInt(shortestDistances::get))
                    .ifPresent((String minDistanceVertex) -> {
                        visited.add(minDistanceVertex);
                        updateShortestDistances(minDistanceVertex, shortestDistances);
                    });

        }

        return shortestDistances;
    }

    private void updateShortestDistances(String vertex, Map<String, Integer> shortestDistances) {
        paths.stream().filter(path -> path.getLeft().equals(vertex) || path.getRight().equals(vertex))
                .forEach((Path path) -> {
                    String neighbor = path.getLeft().equals(vertex) ? path.getRight() : path.getLeft();
                    int weight = path.getWeight();
                    final var newShortestDistances = shortestDistances.get(vertex) + weight;
                    if (newShortestDistances < shortestDistances.get(neighbor)) {
                        shortestDistances.put(neighbor, newShortestDistances);
                    }
                });
    }
}
