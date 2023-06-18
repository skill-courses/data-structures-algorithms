package datastructures.tree.bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinaryAverageTreeTest {

    @Test
    void should_get_tree_height() {
        int[] arr = {10, 12, 8, 9, 7, 6};

        final var tree = new BinaryAverageTree(arr);
        int height =tree.getMaxHeight();
        assertEquals(3, height);
        assertEquals(2, tree.getLeftTreeHeight());
        assertEquals(2, tree.getRightTreeHeight());
    }


    @Test
    void should_generate_binary_average_tree_by_right_rotate() {
        int[] arr = {10, 12, 8, 9, 7, 6};

        final var tree = new BinaryAverageTree(arr);
        assertEquals(8, tree.getRoot().getValue());
        int height =tree.getMaxHeight();
        assertEquals(3, height);
        assertEquals(2, tree.getLeftTreeHeight());
        assertEquals(2, tree.getRightTreeHeight());
    }

    @Test
    void should_generate_binary_average_tree_by_left_rotate() {
        int[] arr = {6, 4, 8, 7, 10, 9, 11};

        final var tree = new BinaryAverageTree(arr);
        assertEquals(8, tree.getRoot().getValue());
        int height =tree.getMaxHeight();
        assertEquals(3, height);
        assertEquals(2, tree.getLeftTreeHeight());
        assertEquals(2, tree.getRightTreeHeight());
    }

    @Test
    void should_generate_binary_average_tree_by_left_and_right_rotate() {
        int[] arr = {10, 11, 7, 6, 8, 9};

        final var tree = new BinaryAverageTree(arr);
        assertEquals(8, tree.getRoot().getValue());
        int height =tree.getMaxHeight();
        assertEquals(3, height);
        assertEquals(2, tree.getLeftTreeHeight());
        assertEquals(2, tree.getRightTreeHeight());
    }


}
