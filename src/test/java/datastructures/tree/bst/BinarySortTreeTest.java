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

}
