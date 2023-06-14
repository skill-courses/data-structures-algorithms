package datastructures.tree.huffma;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class HuffmanTree {

    private final Stack<HuffmanNode> nodes = new Stack<>();

    public HuffmanTree(int[] arr) {
        Arrays.stream(arr).forEach((int item) -> {
            HuffmanNode huffmanNode = new HuffmanNode(item);
            nodes.push(huffmanNode);
        });

        buildHuffmaTree((leftNode, rightNode) -> new HuffmanNode( leftNode.getWeights() + rightNode.getWeights()));
    }

    public HuffmanTree(Byte[] bytes) {
        Arrays.stream(bytes).collect(Collectors.toMap(b -> b, b -> 1, Integer::sum)).forEach((key, value) -> {
            HuffmanNode huffmanNode = new HuffmanNode(Optional.of(key), value);
            nodes.push(huffmanNode);
        });

        buildHuffmaTree((leftNode, rightNode) -> new HuffmanNode(Optional.empty(), leftNode.getWeights() + rightNode.getWeights()));
    }

    private void buildHuffmaTree(BiFunction<HuffmanNode, HuffmanNode, HuffmanNode> buildHuffmanNode) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            final var leftNode = this.nodes.pop();
            final var rightNode = this.nodes.pop();
            HuffmanNode parent = buildHuffmanNode.apply(leftNode, rightNode);
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            this.nodes.push(parent);
        }
    }


    public List<Integer> preOrderWeight() {
        List<HuffmanNode> weights = new ArrayList<>();
        nodes.pop().preOrder(weights);
        return weights.stream().map(HuffmanNode::getWeights).collect(Collectors.toList());
    }

    public List<HuffmanNode> preOrder() {
        List<HuffmanNode> weights = new ArrayList<>();
        nodes.pop().preOrder(weights);
        return weights;
    }

    public Map<Byte, String> buildHuffmaCodes() {
        Map<Byte, String> huffmanCodes = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        nodes.pop().getCodes(huffmanCodes, "", builder);
        return huffmanCodes;
    }
}
