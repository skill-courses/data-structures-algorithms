package datastructures.tree;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

    @Test
    void should_build_huffman_tree_for_array() {
        int[] arr = {45, 70, 12, 7, 9, 1};

        HuffmanTree huffmanTree = new HuffmanTree(arr);

        List<Integer> weights = huffmanTree.preOrderWeight();

        assertEquals(List.of(144, 70, 74, 29, 12, 17, 8, 1, 7, 9, 45), weights);
    }
}
