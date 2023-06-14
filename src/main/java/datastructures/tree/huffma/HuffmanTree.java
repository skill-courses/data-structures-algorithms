package datastructures.tree.huffma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class HuffmanTree {

    private final Stack<HuffmanNode> nodes = new Stack<>();

    public HuffmanTree(int[] arr) {
        Arrays.stream(arr).forEach((int item) -> {
            HuffmanNode huffmanNode = new HuffmanNode(item);
            nodes.push(huffmanNode);
        });

        buildHuffmaTree();
    }

    private void buildHuffmaTree() {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            final var leftNode = this.nodes.pop();
            final var rightNode = this.nodes.pop();
            HuffmanNode parent = new HuffmanNode(leftNode.getWeights() + rightNode.getWeights());
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            this.nodes.push(parent);
        }
    }

    public List<Integer> preOrderWeight() {
        List<Integer> weights = new ArrayList<>();
        nodes.pop().preOrder(weights);
        return weights;
    }
}
