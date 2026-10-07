package com.example.game2048.service;

import com.example.game2048.Direction;
import com.example.game2048.grid.Grid;
import com.example.game2048.grid.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveServiceEdgeCaseTest {

    private int value(Grid grid, int row, int column) {
        Tile tile = grid.getTile(row, column);
        return tile == null ? 0 : tile.getValue();
    }

    @Test
    void shouldMoveRight() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(4));

        assertTrue(new MoveService(grid).move(Direction.RIGHT));

        assertEquals(2, value(grid, 0, 2));
        assertEquals(4, value(grid, 0, 3));
    }

    @Test
    void shouldMoveUpAndDown() {
        Grid grid = new Grid(4);
        grid.setTile(3, 1, new Tile(2));
        MoveService service = new MoveService(grid);

        assertTrue(service.move(Direction.UP));
        assertEquals(2, value(grid, 0, 1));

        assertTrue(service.move(Direction.DOWN));
        assertEquals(2, value(grid, 3, 1));
    }

    @Test
    void shouldMergeUpward() {
        Grid grid = new Grid(4);
        grid.setTile(2, 0, new Tile(8));
        grid.setTile(3, 0, new Tile(8));

        assertTrue(new MoveService(grid).move(Direction.UP));

        assertEquals(16, value(grid, 0, 0));
        assertEquals(0, value(grid, 1, 0));
    }

    @Test
    void shouldNotMergeAFreshlyMergedTileAgain() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(4));
        grid.setTile(0, 1, new Tile(2));
        grid.setTile(0, 2, new Tile(2));

        new MoveService(grid).move(Direction.LEFT);

        assertEquals(4, value(grid, 0, 0));
        assertEquals(4, value(grid, 0, 1));
        assertEquals(0, value(grid, 0, 2));
    }

    @Test
    void shouldNotMergeDifferentValues() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(4));

        assertFalse(new MoveService(grid).move(Direction.LEFT));
    }

    @Test
    void shouldReportScoreOfTheLastMoveOnly() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(2));
        grid.setTile(1, 0, new Tile(4));
        grid.setTile(1, 1, new Tile(4));
        MoveService service = new MoveService(grid);

        service.move(Direction.LEFT);
        assertEquals(12, service.getScoreOfLastMove());   // 4 + 8

        service.move(Direction.LEFT);
        assertEquals(0, service.getScoreOfLastMove());
    }

    @Test
    void shouldWorkOnAGridBiggerThanFour() {
        Grid grid = new Grid(6);
        grid.setTile(2, 5, new Tile(2));
        grid.setTile(2, 4, new Tile(2));

        assertTrue(new MoveService(grid).move(Direction.LEFT));

        assertEquals(4, value(grid, 2, 0));
    }
}