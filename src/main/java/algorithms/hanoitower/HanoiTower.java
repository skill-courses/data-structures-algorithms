package algorithms.hanoitower;

import java.util.List;
import java.util.Stack;

public class HanoiTower {
    private final Stack<Piece> towerA;
    private final Stack<Piece> towerB;
    private final Stack<Piece> towerC;
    private int movedSteps;

    public HanoiTower(List<Piece> pieces) {
        towerA = new Stack<>();
        pieces.stream().sorted().forEach(towerA::push);
        towerB = new Stack<>();
        towerC = new Stack<>();
    }

    public Stack<Piece> getTowerA() {
        return towerA;
    }

    public Stack<Piece> getTowerB() {
        return towerB;
    }

    public Stack<Piece> getTowerC() {
        return towerC;
    }

    public int movePiecesToTowerC() {
        movePieces(towerA.size(), towerA, towerC, towerB);
        return movedSteps;
    }

    private void movePieces(int size, Stack<Piece> source, Stack<Piece> target, Stack<Piece> auxiliary) {
        if (size > 0) {
            movePieces(size - 1, source, auxiliary, target);

            target.push(source.pop());
            movedSteps++;

            movePieces(size - 1, auxiliary, target, source);
        }
    }
}
