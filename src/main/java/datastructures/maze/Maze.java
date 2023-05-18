package datastructures.maze;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Maze {

    private List<Cell> cells;
    private int rowSize;
    private int columnSize;

    public Maze(int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        cells = IntStream.range(0, rowSize)
                .boxed()
                .flatMap(rowIndex -> IntStream.range(0, columnSize)
                        .mapToObj(columnIndex -> new Cell(rowIndex, columnIndex)))
                .collect(Collectors.toList());
        initWall();
    }

    private void initWall() {
        this.cells.forEach(cell -> {
            if (cell.getRowIndex() == 0 || cell.getColumnIndex() == 0 || cell.getRowIndex() == rowSize -1 || cell.getColumnIndex() == columnSize -1 ) {
                cell.setShape(Shape.WALL);
            }
        });
    }

    public List<Cell> getCells() {
        return cells.stream()
                .sorted(Comparator.comparing(Cell::getRowIndex).thenComparing(Cell::getRowIndex))
                .collect(Collectors.toList());
    }
}
