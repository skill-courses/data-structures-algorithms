package datastructures.maze;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MazeTest {

    @Test
    void should_init_maze_without_wall() {
        final Maze maze = new Maze(3, 3);
        List<Cell> cells = maze.getCells();

        assertEquals(9, cells.size());
        assertEquals(Shape.WALL, cells.get(0).getShape());
        assertEquals(Shape.WALL, cells.get(1).getShape());
        assertEquals(Shape.WALL, cells.get(2).getShape());
        assertEquals(Shape.WALL, cells.get(3).getShape());
        assertEquals(Shape.POINT, cells.get(4).getShape());
        assertEquals(Shape.WALL, cells.get(5).getShape());
        assertEquals(Shape.WALL, cells.get(6).getShape());
        assertEquals(Shape.WALL, cells.get(7).getShape());
        assertEquals(Shape.WALL, cells.get(8).getShape());
    }

}
