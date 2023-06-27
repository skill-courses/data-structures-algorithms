package algorithms.horsechess;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HorseChessGame {
    private final List<Cell> cells;

    public HorseChessGame(int rowSize, int columnSize) {
        this.cells = IntStream.range(0, rowSize)
                .boxed()
                .flatMap(rowIndex -> IntStream.range(0, columnSize)
                        .mapToObj(columnIndex -> new Cell(rowIndex, columnIndex)))
                .collect(Collectors.toList());
    }

    public void traversal(int row, int column) {
        Cell cell = Cell.of(row, column);
        this.findCell(cell).ifPresent(item -> item.setVisited(true));
        final var nextCells = this.nextCells(cell);
        while (!nextCells.isEmpty()) {
            Cell nextCell = nextCells.remove(0);
            if (!nextCell.isVisited()) {
                this.traversal(nextCell.getRow(), nextCell.getColumn());
            }
        }
    }

    private Optional<Cell> findCell(Cell cell) {
        return this.cells.stream().filter(cell::equals).findFirst();
    }

    public int getSteps() {
        return (int) this.cells.stream().filter(Cell::isVisited).count();
    }

    public List<Cell> nextCells(Cell cell) {
        return this.cells.stream().filter(item -> item.getRow() != cell.getRow() && item.getColumn() != cell.getColumn()
                && (Math.abs(item.getRow() - cell.getRow()) + Math.abs(item.getColumn() - cell.getColumn())) == 3)
                .collect(Collectors.toList());
    }

    public boolean isWin() {
        return getSteps() == this.cells.size();
    }
}
