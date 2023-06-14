package datastructures.tree.huffma;

import java.util.List;
import java.util.Optional;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private final int weights;
    private Optional<HuffmanNode> left = Optional.empty();
    private Optional<HuffmanNode> right = Optional.empty();

    public HuffmanNode(int weights) {
        this.weights = weights;
    }

    public int getWeights() {
        return weights;
    }

    public Optional<HuffmanNode> getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = Optional.of(left);
    }

    public Optional<HuffmanNode> getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = Optional.of(right);
    }

    @Override
    public int compareTo(HuffmanNode node) {
        return node.weights - this.weights;
    }

    public void preOrder(List<Integer> weights) {
        weights.add(this.weights);
        this.getLeft().ifPresent(node -> node.preOrder(weights));
        this.getRight().ifPresent(node -> node.preOrder(weights));
    }
}
