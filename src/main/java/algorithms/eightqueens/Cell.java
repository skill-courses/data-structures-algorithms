package algorithms.eightqueens;

public class Cell {
    private final int rowIndex;
    private final  int columnIndex;

    public Cell(int rowIndex, int columnIndex) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public boolean isConflict(Cell cell) {
        return this.columnIndex == cell.getColumnIndex() || this.rowIndex == cell.getRowIndex() ||
                Math.abs(this.columnIndex -
                        cell.getColumnIndex()) == Math.abs(this.rowIndex - cell.getRowIndex());
    }
}
