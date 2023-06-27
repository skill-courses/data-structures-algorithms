package algorithms.horsechess;

import java.util.Objects;

public class Cell {
    private int row;
    private int column;
    private boolean visited;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static Cell of(int row, int column) {
        return new Cell(row, column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean isVisited() {
        return visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cell cell = (Cell) o;
        return row == cell.row && column == cell.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
