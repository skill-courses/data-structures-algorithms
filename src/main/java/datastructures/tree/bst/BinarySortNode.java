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
        return left.flatMap((BinarySortNode node) -> {
            if (node.value == value) {
                return Optional.of(this);
            }
            return node.findParent(value);
        }).or(() -> right.flatMap((BinarySortNode node) -> {
            if (node.value == value) {
                return Optional.of(this);
            }
            return node.findParent(value);
        }));
    }

    private static Optional<BinarySortNode> getReplacedNode(BinarySortNode node) {
        if (node.isLeafNode()) {
            return Optional.empty();
        }

        if (node.isOnlyRightNode()) {
            return node.getRight();
        }

        if (node.isOnlyLeftNode()) {
            return node.getLeft();
        }

        final var lefMax = node.getLeft().orElseThrow().getMaxNode();
        node.findParent(lefMax.value).ifPresent(item -> item.deleteSubNode(lefMax.value));
        lefMax.right = node.right;
        lefMax.left = node.left;
        return Optional.of(lefMax);
    }

    public void deleteSubNode(int value) {
        this.left.stream().filter(node -> node.value == value).findFirst()
                .ifPresentOrElse(leftNode -> this.left = getReplacedNode(leftNode),
                        () -> this.right = getReplacedNode(this.right.orElseThrow()));
    }


    private boolean isOnlyRightNode() {
        return this.left.isEmpty() && this.right.isPresent();
    }

    private boolean isOnlyLeftNode() {
        return this.left.isPresent() && this.right.isEmpty();
    }

    private boolean isLeafNode() {
        return this.left.isEmpty() && this.right.isEmpty();
    }

    public BinarySortNode getMinNode() {
        return this.left.map(BinarySortNode::getMinNode).orElse(this);
    }

    public BinarySortNode getMaxNode() {
        return this.right.map(BinarySortNode::getMaxNode).orElse(this);
    }
}
