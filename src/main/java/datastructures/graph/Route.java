package datastructures.graph;

import java.util.Objects;

public class Route {
    private final String start;
    private final String end;
    private int weight;

    public Route(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Route(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public static Route of(String start, String end) {
        return new Route(start, end);
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public boolean contains(String vertex) {
        return this.start.equals(vertex) || this.end.equals(vertex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Route route = (Route) o;
        return start.equals(route.start) && end.equals(route.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
