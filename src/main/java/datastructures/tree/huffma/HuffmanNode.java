package datastructures.tree.huffma;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HuffmanNode implements Comparable<HuffmanNode> {
    private Optional<Byte> data;
    private final int weights;
    private Optional<HuffmanNode> left = Optional.empty();
    private Optional<HuffmanNode> right = Optional.empty();

    public HuffmanNode(int weights) {
        this.weights = weights;
    }

    public HuffmanNode(Optional<Byte> data, int weights) {
        this.data = data;
        this.weights = weights;
    }

    public int getWeights() {
        return weights;
    }

    public Optional<Byte> getData() {
        return data;
    }

    public void setData(Optional<Byte> data) {
        this.data = data;
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

    public void preOrder(List<HuffmanNode> nodes) {
        nodes.add(this);
        this.getLeft().ifPresent(node -> node.preOrder(nodes));
        this.getRight().ifPresent(node -> node.preOrder(nodes));
    }

    public void getCodes(Map<Byte, String> byteMap, String path, StringBuilder builder) {
        StringBuilder newBuilder = new StringBuilder(builder);
        newBuilder.append(path);
        this.data.ifPresentOrElse(d -> {
            byteMap.put(d, newBuilder.toString());
        }, () -> {
            this.getLeft().ifPresent(node -> node.getCodes(byteMap, "0", newBuilder));
            this.getRight().ifPresent(node -> node.getCodes(byteMap, "1", newBuilder));
        });
    }
}
