package datastructures.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {
    protected final Set<Route> routes = new HashSet<>();
    protected Set<String> vertexes = new HashSet<>();

    public void addRoute(String start, String end, int weight) {
        Route route = new Route(start, end, weight);
        vertexes.addAll(Set.of(start, end));
        addRoute(route);
    }

    public void addRoute(Route route) {
        routes.add(route);
    }

    public int getRouteSize() {
        return routes.size();
    }

    public Set<String> getVertexes() {
        return vertexes;
    }

    public Set<Route> getRoutesByVertex(String vertex) {
        return routes.stream().filter(route -> route.getStart().equals(vertex))
                .collect(Collectors.toSet());
    }

    public List<Set<Route>> getPathsByDFS(String start, String end) {
        List<Set<Route>> paths = new ArrayList<>();
        Set<Route> currentPath = new HashSet<>();
        dfs(start, end, currentPath, paths);
        return paths;
    }

    private void dfs(String current, String end, Set<Route> currentPath, List<Set<Route>> paths) {
        if (current.equals(end)) {
            paths.add(new HashSet<>(currentPath));
            return;
        }

        getRoutesByVertex(current).forEach((Route route) -> {
            if (!currentPath.contains(route)) {
                currentPath.add(route);
                dfs(route.getEnd(), end, currentPath, paths);
                currentPath.remove(route);
            }
        });
    }

    public List<Set<Route>> getPathByBFS(String start, String end) {
        List<Set<Route>> paths = new ArrayList<>();
        Queue<List<Route>> queue = new LinkedList<>();

        getRoutesByVertex(start).forEach(route -> addPathToQueue(queue, route, Collections.emptyList()));

        while (!queue.isEmpty()) {
            List<Route> currentPath = queue.poll();
            Route lastRoute = currentPath.get(currentPath.size() - 1);

            if (lastRoute.getEnd().equals(end)) {
                Set<Route> pathSet = new HashSet<>(currentPath);
                paths.add(pathSet);
            } else {
                getRoutesByVertex(lastRoute.getEnd()).stream().filter(route -> !currentPath.contains(route))
                        .forEach(route -> addPathToQueue(queue, route, currentPath));
            }
        }

        return paths;
    }

    private static void addPathToQueue(Queue<List<Route>> queue, Route route, List<Route> currentPath) {
        List<Route> newPath = new ArrayList<>(currentPath);
        newPath.add(route);
        queue.offer(newPath);
    }

    public Set<Route> getMinWeightPaths(String start, String end) {
        return getPathsByDFS(start, end).stream().min(Comparator.comparingInt(path -> path.stream()
                .mapToInt(Route::getWeight)
                .sum())).orElse(Collections.emptySet());
    }
}
