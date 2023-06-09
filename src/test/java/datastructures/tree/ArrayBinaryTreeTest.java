package datastructures.tree;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayBinaryTreeTest {

    @Test
    void should_traverse_array_by_binary_tree_of_pre_order() {
        int[] arr = {1, 2, 3,4, 5, 6, 7};

        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);

        List<Integer> actual = arrayBinaryTree.queryByPreOrder();
        assertEquals(List.of(1, 2, 4, 5, 3, 6, 7), actual);
    }
}
