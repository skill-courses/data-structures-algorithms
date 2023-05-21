package algorithms.eightqueens;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EightQueenTest {

    @Test
    void should_get_92_solutions_for_eight_queen() {
        final EightQueen eightQueen = new EightQueen();
        List<List<Cell>> solutions = eightQueen.getSolutions();

        assertEquals(92, solutions.size());
    }

}
