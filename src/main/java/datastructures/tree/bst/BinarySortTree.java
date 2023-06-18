package datastructures.tree.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BinarySortTree {
    private final BinarySortNode root;

    public BinarySortTree(int[] arr) {
        this.root = new BinarySortNode(arr[0]);

        Arrays.stream(arr).skip(1).forEach((int item) -> {
            final var binarySortNode = new BinarySortNode(item);
            this.root.add(binarySortNode);
        });
    }

    public List<BinarySortNode> infixOrder() {
        List<BinarySortNode> nodes = new ArrayList<>();
        root.infixOrder(nodes);
        return nodes;
    }

    public void delete(int value) {
        Optional<BinarySortNode> nodeOptional = root.findParent(value);
        nodeOptional.ifPresent(node -> node.deleteSubNode(value));
    }

    public int findMinValue() {
        return root.getMinNode().getValue();
    }

    public int findMaxValue() {
        return root.getMaxNode().getValue();
    }
}
