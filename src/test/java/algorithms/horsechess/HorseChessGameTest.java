package algorithms.horsechess;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HorseChessGameTest {

    @Test
    void should_has_8_next_cells() {
        HorseChessGame game = new HorseChessGame(6, 6);
        Cell cell = new Cell(2, 2);
        List<Cell> cells = game.nextCells(cell);
        assertEquals(8, cells.size());
        final var exception = List.of(Cell.of(0, 1), Cell.of(0, 3), Cell.of(1, 0), Cell.of(1, 4),
        Cell.of(3, 0), Cell.of(3, 4), Cell.of(4, 1), Cell.of(4, 3));
        assertEquals(exception, cells);
    }

    @Test
    void should_has_3_next_cells() {
        HorseChessGame game = new HorseChessGame(6, 6);
        Cell cell = new Cell(0, 1);
        List<Cell> cells = game.nextCells(cell);
        assertEquals(3, cells.size());
        final var exception = List.of(Cell.of(1, 3), Cell.of(2, 0), Cell.of(2, 2));
        assertEquals(exception, cells);
    }

    @Test
    void should_play_all_cell_for_6_6() {
        HorseChessGame game = new HorseChessGame(6, 6);
        game.traversal(2, 2);

        int steps = game.getSteps();
        assertEquals(36, steps);
        assertTrue(game.isWin());
    }

    @Test
    void should_play_all_cell_for_3_3() {
        HorseChessGame game = new HorseChessGame(3, 3);
        game.traversal(2, 2);

        int steps = game.getSteps();
        assertEquals(8, steps);
        assertFalse(game.isWin());
    }

}
