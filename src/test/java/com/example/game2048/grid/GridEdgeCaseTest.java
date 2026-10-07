package com.example.game2048.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridEdgeCaseTest {

    @Test
    void shouldRejectCellsOutsideTheGrid() {
        Grid grid = new Grid(4);

        assertThrows(IndexOutOfBoundsException.class, () -> grid.getTile(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.getTile(0, 4));
        assertThrows(IndexOutOfBoundsException.class, () -> grid.setTile(4, 0, new Tile(2)));
    }

    @Test
    void shouldListEmptyPositions() {
        Grid grid = new Grid(2);
        grid.setTile(0, 0, new Tile(2));

        assertEquals(3, grid.getEmptyPositions().size());
    }

    @Test
    void shouldDetectEqualNeighbours() {
        Grid grid = new Grid(2);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(4));
        grid.setTile(1, 0, new Tile(8));
        grid.setTile(1, 1, new Tile(4));   // under the 4 at (0,1)

        assertTrue(grid.hasEqualNeighbours());
    }

    @Test
    void shouldReportNoEqualNeighboursWhenAllDifferent() {
        Grid grid = new Grid(2);
        grid.setTile(0, 0, new Tile(2));
        grid.setTile(0, 1, new Tile(4));
        grid.setTile(1, 0, new Tile(8));
        grid.setTile(1, 1, new Tile(2));

        assertFalse(grid.hasEqualNeighbours());
    }
}