package datastructures.tree.bst;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BinarySortTreeTest {

    @Test
    void should_init_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 4, 5, 6, 8, 9, 12, 15, 20), actual);
    }

    @Test
    void should_delete_leaf_node_in_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(1);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(4, 5, 6, 8, 9, 12, 15, 20), actual);

        tree.delete(20);
        List<Integer> actual1 = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(4, 5, 6, 8, 9, 12, 15), actual1);
    }

    @Test
    void should_delete_right_node_with_single_right_path_in_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(5);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 4, 6, 8, 9, 12, 15, 20), actual);
    }

    @Test
    void should_delete_right_node_wiht_single_left_path_in_binary_sort_tree() {
        int[] arr = {4, 1, 8, 7, 6};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(8);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 4, 6, 7), actual);
    }

    @Test
    void should_delete_left_node_with_single_left_path_in_binary_sort_tree() {
        int[] arr = {10, 9, 5, 8, 4, 7, 6, 2, 1, 3, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(9);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 10, 12), actual);
    }

    @Test
    void should_delete_left_node_with_single_right_path_in_binary_sort_tree() {
        int[] arr = {10, 9, 5, 8, 7, 6, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(5);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(6, 7, 8, 9, 10, 12), actual);
    }

    @Test
    void should_delete_left_node_with_double_path_in_binary_sort_tree() {
        int[] arr = {10, 9, 5, 8, 4, 7, 6, 2, 1, 3, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(5);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 2, 3, 4, 6, 7, 8, 9, 10, 12), actual);
    }

    @Test
    void should_delete_double_node_in_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);

        tree.delete(9);
        List<Integer> actual = tree.infixOrder().stream().map(BinarySortNode::getValue).collect(Collectors.toList());
        assertEquals(List.of(1, 4, 5, 6, 8, 12, 15, 20), actual);
    }

    @Test
    void should_find_min_node_value_in_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);
        int min = tree.findMinValue();

        assertEquals(1, min);
    }

    @Test
    void should_find_max_node_value_in_binary_sort_tree() {
        int[] arr = {4, 1, 5, 9, 15, 6, 8, 20, 12};
        BinarySortTree tree = new BinarySortTree(arr);
        int min = tree.findMaxValue();

        assertEquals(20, min);
    }

}
