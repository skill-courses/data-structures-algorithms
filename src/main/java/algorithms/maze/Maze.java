package algorithms.maze;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import datastructures.stack.NoPathException;
import datastructures.stack.NotFoundCellException;

public class Maze {

    private final List<Cell> cells;
    private final int rowSize;
    private final int columnSize;

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
        this.cells.forEach((Cell cell) -> {
            if (cell.getColumnIndex() == 0 || cell.getColumnIndex() == columnSize -1) {
                cell.setShape(Shape.VERTICAL_WALL);
            }

            if (cell.getRowIndex() == 0 || cell.getRowIndex() == rowSize -1) {
                cell.setShape(Shape.HORIZONTAL_WALL);
            }
        });
    }

    public List<Cell> getCells() {
        return cells.stream()
                .sorted(Comparator.comparing(Cell::getRowIndex).thenComparing(Cell::getRowIndex))
                .collect(Collectors.toList());
    }

    public void addWalls(Iterable<Cell> wallCells) {
        wallCells.forEach(cell -> getCell(cell).setShape(cell.getShape()));
    }

    private Cell getCell(Cell cell) {
        return this.getCells().stream().filter(cell::sameLocation).findFirst().orElseThrow(
                NotFoundCellException::new);
    }

    private Optional<Cell> getDownCell(Cell cell) {
        return this.getCells().stream().filter(it -> it.isDownCell(cell)).findFirst();
    }

    private Optional<Cell> getRightCell(Cell cell) {
        return this.getCells().stream().filter(it -> it.isRightCell(cell)).findFirst();
    }

    private Optional<Cell> getUpCell(Cell cell) {
        return this.getCells().stream().filter(it -> it.isUpCell(cell)).findFirst();
    }

    private Optional<Cell> getLeftCell(Cell cell) {
        return this.getCells().stream().filter(it -> it.isLeftCell(cell)).findFirst();
    }

    public List<Cell> getPath(Cell start, Cell end) {
        this.explore(start, end);
        return this.getCells().stream().filter(Cell::isPath).collect(Collectors.toList());
    }

    private boolean explore(Cell start, Cell end) {
        final Cell current = this.getCell(start);
        if (current.sameLocation(end)) {
            current.setShape(Shape.REACHABLE);
            return true;
        } else {
            if (current.reachable()) {
                current.setShape(Shape.REACHABLE);
                final Optional<Cell> downCell = getDownCell(current);
                final Optional<Cell> rightCell = getRightCell(current);
                final Optional<Cell> upCell = getUpCell(current);
                final Optional<Cell> leftCell = getLeftCell(current);
                if (downCell.isPresent() && explore(downCell.get(), end)) {
                    return true;
                } else if (rightCell.isPresent() && explore(rightCell.get(), end)) {
                    return true;
                } else if (upCell.isPresent() && explore(upCell.get(), end)) {
                    return true;
                } else if (leftCell.isPresent() && explore(leftCell.get(), end)) {
                    return true;
                } else {
                    throw new NoPathException();
                }
            }
        }
        return false;
    }
}
