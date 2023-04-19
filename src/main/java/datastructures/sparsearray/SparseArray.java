package datastructures.sparsearray;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SparseArray {

    private static final int SPARSE_ARRAY_COLUMN = 3;
    private static final int START_INDEX = 0;
    private final int row;
    private final int column;
    private final int count;
    private final int[][] sparseArr;

    public SparseArray(int[][] twoDimensionalArr) {
        if (twoDimensionalArr == null) {
            throw new RuntimeException("Two Dimensional is null");
        }

        this.row = twoDimensionalArr.length;
        this.column = twoDimensionalArr[START_INDEX].length;
        this.count = Arrays.stream(twoDimensionalArr).flatMapToInt(Arrays::stream)
                .filter(i -> i != 0).map(i -> 1).sum();
        this.sparseArr = new int[count + 1][SPARSE_ARRAY_COLUMN];
        this.sparseArr[START_INDEX] = new int[] {this.row, this.column, count};

        setElementsToSparseArr(twoDimensionalArr);
    }

    private void setElementsToSparseArr(int[][] twoDimensionalArr) {
        final int[][] elements = IntStream.range(START_INDEX, this.row).boxed()
                .flatMap(rowIndex -> IntStream.range(START_INDEX, this.column)
                        .filter(columnIndex -> twoDimensionalArr[rowIndex][columnIndex] != 0)
                        .mapToObj(columnIndex -> new int[] {rowIndex, columnIndex,
                                twoDimensionalArr[rowIndex][columnIndex]}))
                .toArray(int[][]::new);
        System.arraycopy(elements, START_INDEX, this.sparseArr, 1, count);
    }

    public int[][] getSparseArr() {
        return Arrays.copyOf(this.sparseArr, count + 1);
    }

    public int[][] covertTo2dimensionalArr() {
        int rowSize = this.sparseArr[0][0];
        int columnSize = this.sparseArr[0][1];
        int[][] twoDimensionalArr = new int[rowSize][columnSize];
        Arrays.stream(this.sparseArr)
                .skip(1)
                .forEach(item -> twoDimensionalArr[item[0]][item[1]] = item[2]);
        return twoDimensionalArr;
    }
}
