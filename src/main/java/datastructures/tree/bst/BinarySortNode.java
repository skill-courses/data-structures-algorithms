package datastructures.tree.bst;

import java.util.List;
import java.util.Optional;

public class BinarySortNode {
    private final int value;
    private Optional<BinarySortNode> left = Optional.empty();
    private Optional<BinarySortNode> right = Optional.empty();

    public BinarySortNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void add(BinarySortNode binarySortNode) {
        if (this.value > binarySortNode.value) {
            this.left.ifPresentOrElse(leftNode -> leftNode.add(binarySortNode),
                    () -> this.left = Optional.of(binarySortNode));
        } else {
            this.right.ifPresentOrElse(rightNode -> rightNode.add(binarySortNode),
                    () -> this.right = Optional.of(binarySortNode));
        }
    }

    public void infixOrder(List<BinarySortNode> nodes) {
        this.left.ifPresent(node -> node.infixOrder(nodes));
        nodes.add(this);
        this.right.ifPresent(node -> node.infixOrder(nodes));
    }
}
