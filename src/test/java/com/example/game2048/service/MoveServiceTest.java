package com.example.game2048.service;

import com.example.game2048.Direction;
import com.example.game2048.grid.Grid;
import com.example.game2048.grid.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveServiceTest {

    @Test
    void shouldMoveTileLeftThroughEmptyCells() {
        Grid grid = new Grid(4);
        grid.setTile(0, 3, new Tile(2));

        MoveService service = new MoveService(grid);

        assertTrue(service.move(Direction.LEFT));
        assertEquals(2, grid.getTile(0, 0).getValue());
        assertTrue(grid.isEmpty(0, 3));
    }

    @Test
    void shouldMergeEqualTilesWhenMovingLeft() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(2));

        MoveService service = new MoveService(grid);

        assertTrue(service.move(Direction.LEFT));
        assertEquals(4, grid.getTile(0, 0).getValue());
        assertTrue(grid.isEmpty(0, 1));
    }

    @Test
    void shouldNotMoveWhenBoardAlreadyAligned() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));

        MoveService service = new MoveService(grid);

        assertFalse(service.move(Direction.LEFT));
        assertEquals(2, grid.getTile(0, 0).getValue());
    }

    @Test
    void shouldMergeEachPairOnlyOnce() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(2));
        grid.setTile(0, 2, new Tile(2));
        grid.setTile(0, 3, new Tile(2));

        MoveService service = new MoveService(grid);

        assertTrue(service.move(Direction.LEFT));
        assertEquals(4, grid.getTile(0, 0).getValue());
        assertEquals(4, grid.getTile(0, 1).getValue());
    }
}
