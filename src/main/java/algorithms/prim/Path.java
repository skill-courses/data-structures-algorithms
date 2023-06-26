package algorithms.prim;

import java.util.Objects;
import java.util.Set;

public class Path {
    private String left;
    private String right;
    private int weight;

    public Path(String left, String right, int weight) {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }

    public Path(String left, String right) {
        this.left = left;
        this.right = right;
    }

    public String getLeft() {
        return left;
    }

    public String getRight() {
        return right;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public static Path of(String left, String right) {
        return new Path(left, right);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Path path = (Path) o;
        return Set.of(right, left).equals(Set.of(path.right, path.left));
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
