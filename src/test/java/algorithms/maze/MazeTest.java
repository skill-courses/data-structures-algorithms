package algorithms.maze;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import datastructures.stack.NoPathException;
import org.junit.jupiter.api.Test;

class MazeTest {

    @Test
    void should_init_maze_without_wall() {
        final Maze maze = new Maze(3, 3);
        List<Cell> cells = maze.getCells();

        assertEquals(9, cells.size());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(0).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(1).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(2).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(3).getShape());
        assertEquals(Shape.POINT, cells.get(4).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(5).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(6).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(7).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(8).getShape());
    }

    @Test
    void should_add_wall_for_maze() {
        final Maze maze = new Maze(4, 4);
        final Cell cell1 = new Cell(1, 1);
        cell1.setShape(Shape.HORIZONTAL_WALL);
        final Cell cell2 = new Cell(2, 2);
        cell2.setShape(Shape.HORIZONTAL_WALL);
        maze.addWalls(List.of(cell1, cell2));
        List<Cell> cells = maze.getCells();


        assertEquals(16, cells.size());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(0).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(1).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(2).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(3).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(4).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(5).getShape());
        assertEquals(Shape.POINT, cells.get(6).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(7).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(8).getShape());
        assertEquals(Shape.POINT, cells.get(9).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(10).getShape());
        assertEquals(Shape.VERTICAL_WALL, cells.get(11).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(12).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(13).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(14).getShape());
        assertEquals(Shape.HORIZONTAL_WALL, cells.get(15).getShape());
    }

    @Test
    void should_get_path_from_start_location_to_end_location_for_maze_without_wall() {
        final Maze maze = new Maze(4, 4);
        final Cell start = new Cell(1, 1);
        final Cell end = new Cell(2, 2);
        maze.getPath(start, end);
        List<Cell> path = maze.getPath(start, end);
        assertEquals(3, path.size());
        assertTrue(path.get(0).sameLocation(new Cell(1, 1)));
        assertTrue(path.get(1).sameLocation(new Cell(2, 1)));
        assertTrue(path.get(2).sameLocation(new Cell(2, 2)));
    }

    @Test
    void should_has_dead_path_when_start_location_not_arrive_end_location() {
        final Maze maze = new Maze(4, 4);
        final Cell start = new Cell(1, 1);
        final Cell end = new Cell(2, 2);
        final Cell cell1 = new Cell(1, 2);
        cell1.setShape(Shape.VERTICAL_WALL);
        final Cell cell2 = new Cell(2, 1);
        cell2.setShape(Shape.HORIZONTAL_WALL);
        maze.addWalls(List.of(cell1, cell2));

        assertThrows(NoPathException.class, () -> maze.getPath(start, end));
    }

    @Test
    void should_get_path_from_start_location_to_end_location_has_wall() {
        final Maze maze = new Maze(10, 10);
        Cell wall1 = new Cell(2, 1);
        wall1.setShape(Shape.HORIZONTAL_WALL);
        Cell wall2 = new Cell(5, 2);
        wall2.setShape(Shape.HORIZONTAL_WALL);
        Cell wall3 = new Cell(5, 3);
        wall3.setShape(Shape.HORIZONTAL_WALL);
        Cell wall4 = new Cell(4, 3);
        wall4.setShape(Shape.VERTICAL_WALL);
        Cell wall5 = new Cell(8, 3);
        wall5.setShape(Shape.VERTICAL_WALL);
        maze.addWalls(List.of(wall1, wall2, wall3, wall4, wall5));

        final Cell start = new Cell(1, 1);
        final Cell end = new Cell(8, 8);
        final List<Cell> path = maze.getPath(start, end);

        assertEquals(19, path.size());
    }


}
