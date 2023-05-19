package datastructures.maze;


public class Cell {
    private final int rowIndex;
    private final int columnIndex;
    private Shape shape;

    public Cell(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.shape = Shape.POINT;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public boolean sameLocation(Cell cell) {
        return this.rowIndex == cell.getRowIndex() && this.columnIndex == cell.getColumnIndex();
    }

    public boolean isDownCell(Cell cell) {
        return (this.rowIndex == cell.getRowIndex() + 1) && (this.columnIndex == cell.getColumnIndex());
    }

    public boolean isRightCell(Cell cell) {
        return (this.rowIndex == cell.getRowIndex()) && (this.columnIndex == cell.getColumnIndex() + 1);
    }

    public boolean isUpCell(Cell cell) {
        return (this.rowIndex == cell.getRowIndex() - 1) && (this.columnIndex == cell.getColumnIndex());
    }

    public boolean isLeftCell(Cell cell) {
        return (this.rowIndex == cell.getRowIndex()) && (this.columnIndex == cell.getColumnIndex() - 1);
    }

    public boolean reachable() {
        return this.getShape() == Shape.POINT;
    }

    public boolean isPath() {
        return this.getShape() == Shape.REACHABLE;
    }
}
