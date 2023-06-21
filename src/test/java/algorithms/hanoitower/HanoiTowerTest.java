package algorithms.hanoitower;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HanoiTowerTest {

    @Test
    void should_init_hanoi_tower() {
        List<Piece> pieces = List.of(Piece.of(1), Piece.of(2), Piece.of(3));

        HanoiTower hanoiTower = new HanoiTower(pieces);
        final var towerA = hanoiTower.getTowerA();
        final var towerB = hanoiTower.getTowerB();
        final var towerC = hanoiTower.getTowerC();
        assertEquals(3, towerA.size());
        assertEquals(0, towerB.size());
        assertEquals(0, towerC.size());

        assertEquals(1, towerA.pop().getWeight());
        assertEquals(2, towerA.pop().getWeight());
        assertEquals(3, towerA.pop().getWeight());
    }

    @Test
    void should_move_one_piece_from_A_to_C() {
        List<Piece> pieces = List.of(Piece.of(1));

        HanoiTower hanoiTower = new HanoiTower(pieces);
        assertEquals(1, hanoiTower.getTowerA().size());
        assertEquals(0, hanoiTower.getTowerC().size());

        int steps = hanoiTower.movePiecesToTowerC();
        assertEquals(1, steps);
        assertEquals(0, hanoiTower.getTowerA().size());
        final var towerC = hanoiTower.getTowerC();
        assertEquals(1, towerC.size());
        assertEquals(1, towerC.pop().getWeight());
    }

    @Test
    void should_move_two_pieces_from_A_to_C() {
        List<Piece> pieces = List.of(Piece.of(1), Piece.of(2));

        HanoiTower hanoiTower = new HanoiTower(pieces);
        assertEquals(2, hanoiTower.getTowerA().size());
        assertEquals(0, hanoiTower.getTowerC().size());

        int steps = hanoiTower.movePiecesToTowerC();
        assertEquals(3, steps);
        assertEquals(0, hanoiTower.getTowerA().size());
        final var towerC = hanoiTower.getTowerC();
        assertEquals(2, towerC.size());
        assertEquals(1, towerC.pop().getWeight());
        assertEquals(2, towerC.pop().getWeight());
    }

    @Test
    void should_move_three_pieces_from_A_to_C() {
        List<Piece> pieces = List.of(Piece.of(1), Piece.of(2), Piece.of(3));

        HanoiTower hanoiTower = new HanoiTower(pieces);
        assertEquals(3, hanoiTower.getTowerA().size());
        assertEquals(0, hanoiTower.getTowerC().size());

        int steps = hanoiTower.movePiecesToTowerC();
        assertEquals(7, steps);
        assertEquals(0, hanoiTower.getTowerA().size());
        final var towerC = hanoiTower.getTowerC();
        assertEquals(3, towerC.size());
        assertEquals(1, towerC.pop().getWeight());
        assertEquals(2, towerC.pop().getWeight());
        assertEquals(3, towerC.pop().getWeight());
    }

}
