package algorithms.mst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MininumCostSpanningTree {
    private Set<Path> paths = new HashSet<>();
    private Set<String> vertexes = new HashSet<>();
    private Map<String, Set<String>> connectedVertexes = new HashMap<>();

    public void add(Path path) {
        Set<String> rights = connectedVertexes.getOrDefault(path.getRight(), Set.of(path.getRight()));
        Set<String> lefts = connectedVertexes.getOrDefault(path.getLeft(), Set.of(path.getLeft()));

        //判断加入的route可以构成一个环状路径
        if (!this.hasPath(path) && !rights.equals(lefts)) {
            this.paths.add(path);
            vertexes.addAll(Set.of(path.getRight(), path.getLeft()));

            // 合并连通分量
            Set<String> mergedVertexes = Stream.concat(rights.stream(), lefts.stream()).collect(
                    Collectors.toSet());
            mergedVertexes.forEach(vertex -> connectedVertexes.put(vertex, mergedVertexes));
        }
    }

    public Set<Path> getPaths() {
        return this.paths;
    }

    public Set<String> getVertexes() {
        return vertexes;
    }

    public boolean hasPath(Path path) {
        return this.paths.stream().filter(path::equals).findFirst().isPresent();
    }
}
