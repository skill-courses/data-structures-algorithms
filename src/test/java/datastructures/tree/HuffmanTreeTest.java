package datastructures.tree;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import datastructures.tree.huffma.HuffmanNode;
import datastructures.tree.huffma.HuffmanTree;
import org.junit.jupiter.api.Test;

class HuffmanTreeTest {

    @Test
    void should_build_huffman_tree_for_array() {
        int[] arr = {45, 70, 12, 7, 9, 1};

        HuffmanTree huffmanTree = new HuffmanTree(arr);

        List<Integer> weights = huffmanTree.preOrderWeight();

        assertEquals(List.of(144, 70, 74, 29, 12, 17, 8, 1, 7, 9, 45), weights);
    }

    @Test
    void should_build_huffma_code_tree_for_bytes() {
        Byte[] bytes = {1, 2, 3, 2, 1, 3, 4, 5, 4, 3, 2};

        final var huffmanTree = new HuffmanTree(bytes);

        List<HuffmanNode> nodes = huffmanTree.preOrder();
        assertEquals(9, nodes.size());
        assertFalse(nodes.get(0).getData().isPresent());
        assertEquals(11, nodes.get(0).getWeights());
        assertTrue(nodes.get(8).getData().isPresent());
    }
}
