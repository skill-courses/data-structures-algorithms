package datastructures.tree;

import java.util.ArrayList;
import java.util.List;

public class ArrayBinaryTree {
    private final int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public List<Integer> queryByPreOrder() {
        List<Integer> result = new ArrayList<>();
        preOrder(0, result);
        return result;
    }

    private void preOrder(int index, List<Integer> result) {
        result.add(this.arr[index]);

        int leftNodeIndex = index * 2 + 1;
        if (leftNodeIndex < this.arr.length) {
            preOrder(leftNodeIndex, result);
        }

        int rightNodeIndex = index * 2 + 2;
        if (rightNodeIndex < arr.length) {
            preOrder(rightNodeIndex, result);
        }
    }
}
