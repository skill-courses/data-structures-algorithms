package algorithms.eightqueens;

import java.util.ArrayList;
import java.util.List;

public class EightQueen {
    private final List<List<Cell>> solutions = new ArrayList<>();

    public EightQueen() {
        this.setQueen(0, new ArrayList<>());
    }

    public List<List<Cell>> getSolutions() {
        return new ArrayList<>(solutions);
    }

    private void setQueen(int queenX, List<Cell> currentQueens) {
        final int QUEUE_SIZE = 8;
        if (queenX == QUEUE_SIZE) {
            solutions.add(new ArrayList<>(currentQueens));
            return;
        }

        for (int col = 0; col < QUEUE_SIZE; col++) {
            Cell queenCell = new Cell(queenX, col);
            if (isPlaceQueen(queenCell, currentQueens)) {
                currentQueens.add(queenCell);
                setQueen(queenX + 1, currentQueens);
                currentQueens.remove(currentQueens.size() - 1);
            }
        }
    }

    private static boolean isPlaceQueen(Cell cell, List<Cell> currentQueens) {
        return currentQueens.stream().filter(queen -> queen.isConflict(cell)).findFirst().isEmpty();
    }

}
