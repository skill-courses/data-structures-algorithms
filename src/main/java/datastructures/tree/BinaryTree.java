package datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private final TreeNode root;

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public List<TreeNode> getNodesByPreOrder() {
        List<TreeNode> nodes = new ArrayList<>();
        root.preOrder(nodes);
        return nodes;
    }

    public List<TreeNode> getNodesByInOrder() {
        List<TreeNode> nodes = new ArrayList<>();
        root.inOrder(nodes);
        return nodes;
    }

    public List<TreeNode> getNodesByPostOrder() {
        List<TreeNode> nodes = new ArrayList<>();
        root.postOrder(nodes);
        return nodes;
    }
}
