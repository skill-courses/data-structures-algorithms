package datastructures.tree.bst;

import java.util.Arrays;

public class BinaryAverageTree {
    private final BinarySortNode root;

    public BinaryAverageTree(int[] arr) {
        this.root = new BinarySortNode(arr[0]);

        Arrays.stream(arr).skip(1).forEach((int item) -> {
            final var binarySortNode = new BinarySortNode(item);
            this.root.add(binarySortNode);

            if (getLeftTreeHeight() - getRightTreeHeight() > 1) {
                this.root.getLeft().ifPresent(leftNode -> {
                    if (leftNode.getRightHeight() > leftNode.getLeftHeight()) {
                        leftNode.leftRotate();
                    }
                });

                root.rightRotate();
            }

            if (getRightTreeHeight() - getLeftTreeHeight() > 1) {
                this.root.getRight().ifPresent(rightNode -> {
                    if (rightNode.getLeftHeight() > rightNode.getRightHeight()) {
                        rightNode.rightRotate();
                    }
                });
                root.leftRotate();
            }
        });
    }

    public BinarySortNode getRoot() {
        return root;
    }

    public int getMaxHeight() {
        return root.height();
    }

    public int getLeftTreeHeight() {
        return root.getLeft().map(BinarySortNode::height).orElse(0);
    }

    public int getRightTreeHeight() {
        return root.getRight().map(BinarySortNode::height).orElse(0);
    }
}
