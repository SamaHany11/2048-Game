package com.example.game2048.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void newGridShouldBeEmpty() {
        Grid grid = new Grid(4);

        assertEquals(4, grid.getSize());
        assertFalse(grid.isFull());
        assertTrue(grid.isEmpty(0, 0));
    }

    @Test
    void shouldSetAndGetTile() {
        Grid grid = new Grid(4);
        Tile tile = new Tile(2);

        grid.setTile(1, 2, tile);

        assertSame(tile, grid.getTile(1, 2));
        assertFalse(grid.isEmpty(1, 2));
    }

    @Test
    void shouldClearGrid() {
        Grid grid = new Grid(4);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(3, 3, new Tile(4));

        grid.clear();

        assertTrue(grid.isEmpty(0, 0));
        assertTrue(grid.isEmpty(3, 3));
        assertFalse(grid.isFull());
    }

    @Test
    void shouldRejectInvalidSize() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(1));
    }
}
