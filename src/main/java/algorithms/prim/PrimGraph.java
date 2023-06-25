package algorithms.prim;

import java.util.*;

import datastructures.graph.Graph;
import datastructures.graph.Route;

public class PrimGraph extends Graph {

    public Set<Route> getShortestPaths() {
        Set<String> visited = new HashSet<>();
        Set<Route> shortestPaths = new HashSet<>();

        getShortestRoute().ifPresent(route -> {
            addPath(visited, shortestPaths, route);
            this.routes.remove(route);
        });


        Set<Route> tempPaths = new HashSet<>();
        while (visited.size() < this.vertexes.size()) {
            tempPaths.stream().filter(path -> containsOnlyOneVertex(visited, path))
                    .forEach(path -> addPath(visited, shortestPaths, path));

            getShortestRoute().ifPresent(route -> {
                if (containsOnlyOneVertex(visited, route)){
                    addPath(visited, shortestPaths, route);
                } else if (!visited.contains(route.getStart()) && !visited.contains(route.getEnd())){
                    tempPaths.add(route);
                }
                this.routes.remove(route);
            });
        }

        return shortestPaths;
    }

    private void addPath(Set<String> visited, Set<Route> shortestPaths, Route route) {
        shortestPaths.add(route);
        visited.addAll(Set.of(route.getStart(), route.getEnd()));
    }

    private boolean containsOnlyOneVertex(Set<String> visited, Route route) {
        return (visited.contains(route.getStart()) && !visited.contains(route.getEnd())) ||
                (visited.contains(route.getEnd()) && !visited.contains(route.getStart()));
    }

    private Optional<Route> getShortestRoute() {
        return this.routes.stream().min(Comparator.comparing(Route::getWeight));
    }
}
