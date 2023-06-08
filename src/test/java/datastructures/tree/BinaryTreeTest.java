package datastructures.tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTest {

    private static BinaryTree binaryTree;

    @BeforeAll
    static void init() {
        TreeNode root = new TreeNode(1, "宋江");
        TreeNode root1 = new TreeNode(2, "卢俊义");
        TreeNode root2 = new TreeNode(3, "无用");
        TreeNode root3 = new TreeNode(4, "林冲");

        root.setLeft(root1);
        root.setRight(root2);
        root2.setRight(root3);
        binaryTree = new BinaryTree(root);
    }

    @Test
    void should_get_nodes_by_pre_order() {
        List<TreeNode> nodeByPreOrder = binaryTree.getNodesByPreOrder();

        List<Integer> ids = nodeByPreOrder.stream().map(TreeNode::getId).collect(Collectors.toList());
        assertEquals(List.of(1, 2, 3, 4), ids);
    }

    @Test
    void should_get_nodes_by_in_order() {
        List<TreeNode> nodeByPreOrder = binaryTree.getNodesByInOrder();

        List<Integer> ids = nodeByPreOrder.stream().map(TreeNode::getId).collect(Collectors.toList());
        assertEquals(List.of(2, 1, 3, 4), ids);
    }

    @Test
    void should_get_nodes_by_post_order() {
        List<TreeNode> nodeByPreOrder = binaryTree.getNodesByPostOrder();

        List<Integer> ids = nodeByPreOrder.stream().map(TreeNode::getId).collect(Collectors.toList());
        assertEquals(List.of(2, 4, 3, 1), ids);
    }
}