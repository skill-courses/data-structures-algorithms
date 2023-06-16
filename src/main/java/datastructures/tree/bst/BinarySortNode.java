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

    public Optional<BinarySortNode> getLeft() {
        return left;
    }

    public Optional<BinarySortNode> getRight() {
        return right;
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

    public Optional<BinarySortNode> findParent(int value) {
        return left.flatMap(node -> {
            if (node.value == value) {
                return Optional.of(this);
            }
            return node.findParent(value);
        }).or(() -> right.flatMap(node -> {
            if (node.value == value) {
                return Optional.of(this);
            }
            return node.findParent(value);
        }));
    }

    public void deleteSubNode(int value) {
        this.left.ifPresent(leftNode -> {
            if (leftNode.isTargetLeafNode(value)) {
                this.left = Optional.empty();
            }
        });

        this.right.ifPresent(rightNode -> {
            if (rightNode.isTargetLeafNode(value)) {
                this.right = Optional.empty();
            }
        });
    }

    private boolean isTargetLeafNode(int value) {
        return this.value == value && this.isLeafNode();
    }

    private boolean isLeafNode() {
        return this.left.isEmpty() && this.right.isEmpty();
    }
}
