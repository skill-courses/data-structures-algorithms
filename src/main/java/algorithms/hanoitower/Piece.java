package algorithms.hanoitower;

public class Piece implements Comparable<Piece> {
    private final int weight;

    public Piece(int weight) {
        this.weight = weight;
    }

    public static Piece of(int weight) {
        return new Piece(weight);
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Piece piece) {
        return piece.weight - this.weight;
    }
}
