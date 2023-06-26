package algorithms.spg;

import java.util.*;
import java.util.stream.Collectors;

import algorithms.mst.Path;

public class ShortestPathsGraph {
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

    public Optional<Path> getPath(Path path) {
        return this.paths.stream().filter(path::equals).findFirst();
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

    public Map<String, Map<String, Integer>> getShortestDistance() {
        Map<String, Map<String, Integer>> shortestPaths = initShortestPaths();

        final int INFINITY = Integer.MAX_VALUE;
        for (String first : vertexes) {
            for (String second : vertexes) {
                for (String third : vertexes) {
                    int distance23 = shortestPaths.get(second).get(first);
                    int distance13 = shortestPaths.get(first).get(third);
                    // 排除无穷大的情况
                    if (distance23 == INFINITY || distance13 == INFINITY) {
                        continue;
                    }

                    // 计算最短路径
                    int shortestDistance = distance23 + distance13;
                    if (shortestDistance < shortestPaths.get(second).get(third)) {
                        shortestPaths.get(second).put(third, shortestDistance);
                    }
                }
            }
        }

        return shortestPaths;
    }

    private Map<String, Map<String, Integer>> initShortestPaths() {
        return vertexes.stream().collect(Collectors.toMap(start -> start, start -> this.vertexes.stream()
                .collect(Collectors.toMap(end -> end, end -> {
                    if (end.equals(start)) {
                        return 0;
                    }
                    Path temp = Path.of(start, end);
                    return getPath(temp).map(Path::getWeight).orElse(Integer.MAX_VALUE);
                }))));
    }
}
