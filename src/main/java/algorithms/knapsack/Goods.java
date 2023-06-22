package algorithms.knapsack;

public class Goods implements Comparable<Goods> {
    private final String name;
    private final int weight;
    private final int value;

    public Goods(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Goods good) {
        return good.getWeight() - this.getWeight();
    }
}
