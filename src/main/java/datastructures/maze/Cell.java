package datastructures.maze;

import java.util.Objects;

public class Cell {
    private int rowIndex = 0;
    private int columnIndex = 0;
    private Shape shape;

    public Cell(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.shape = Shape.POINT;
    }

    public static Cell ofWall(int rowIndex, int columnIndex) {
        final Cell cell = new Cell(rowIndex, columnIndex);
        cell.setShape(Shape.WALL);
        return cell;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
