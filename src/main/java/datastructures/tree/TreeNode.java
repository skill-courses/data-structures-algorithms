package datastructures.tree;

import java.util.List;
import java.util.Optional;

public class TreeNode {
    private final int id;
    private final String name;
    private Optional<TreeNode> left = Optional.empty();
    private Optional<TreeNode> right = Optional.empty();

    public TreeNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<TreeNode> getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = Optional.of(left);
    }

    public Optional<TreeNode> getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = Optional.of(right);
    }

    public void preOrder(List<TreeNode> nodes) {
        nodes.add(this);
        this.getLeft().ifPresent(node -> node.preOrder(nodes));
        this.getRight().ifPresent(node -> node.preOrder(nodes));
    }

    public void inOrder(List<TreeNode> nodes) {
        this.getLeft().ifPresent(node -> node.inOrder(nodes));
        nodes.add(this);
        this.getRight().ifPresent(node -> node.inOrder(nodes));
    }

    public void postOrder(List<TreeNode> nodes) {
        this.getLeft().ifPresent(node -> node.postOrder(nodes));
        this.getRight().ifPresent(node -> node.postOrder(nodes));
        nodes.add(this);
    }

    public Optional<TreeNode> preSearch(int no) {
        if (this.id == no) {
            return Optional.of(this);
        }

        return left.flatMap(node -> node.preSearch(no))
                .or(() -> right.flatMap(node -> node.preSearch(no)));
    }

    public Optional<TreeNode> inSearch(int no) {
        Optional<TreeNode> result = left.flatMap(node -> node.inSearch(no));
        if (result.isPresent()) {
            return result;
        }

        if (this.id == no) {
            return Optional.of(this);
        }

        return right.flatMap(node -> node.inSearch(no));
    }

    public Optional<TreeNode> postSearch(int no) {
        return left.flatMap(node -> node.preSearch(no))
                .or(() -> right.flatMap(node -> node.preSearch(no)))
                .or(() -> {
                    if (this.id == no) {
                        return Optional.of(this);
                    }
                    return Optional.empty();
                });
    }
}
