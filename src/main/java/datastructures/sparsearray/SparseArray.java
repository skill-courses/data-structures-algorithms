package datastructures.sparsearray;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SparseArray {

    private static final int SPARSE_ARRAY_COLUMN = 3;
    private final int row;
    private final int column;
    private final int count;
    private final int[][] sparseArr;

    public SparseArray(int[][] twoDimensionalArr) {
        if (twoDimensionalArr == null) {
            throw new RuntimeException("Two Dimensional is null");
        }

        this.row = twoDimensionalArr.length;
        this.column = twoDimensionalArr[0].length;
        this.count = Arrays.stream(twoDimensionalArr).flatMapToInt(Arrays::stream)
                .filter(i -> i != 0).map(i -> 1).sum();
        this.sparseArr = new int[count + 1][SPARSE_ARRAY_COLUMN];
        this.sparseArr[0] = new int[] {this.row, this.column, count};

        setElementsToSparseArr(twoDimensionalArr);
    }

    private void setElementsToSparseArr(int[][] twoDimensionalArr) {
        final int[][] elements = IntStream.range(0, this.row).boxed()
                .flatMap(rowIndex -> IntStream.range(0, this.column)
                        .filter(columnIndex -> twoDimensionalArr[rowIndex][columnIndex] != 0)
                        .mapToObj(columnIndex -> new int[] {rowIndex, columnIndex,
                                twoDimensionalArr[rowIndex][columnIndex]}))
                .toArray(int[][]::new);
        System.arraycopy(elements, 0, this.sparseArr, 1, count);
    }

    public int[][] getSparseArr() {
        return Arrays.copyOf(this.sparseArr, count + 1);
    }
}
