package com.example.game2048.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void shouldCreateTileWithPositiveValue() {
        Tile tile = new Tile(2);
        assertEquals(2, tile.getValue());
    }

    @Test
    void shouldDoubleTileWhenMerged() {
        Tile tile = new Tile(4);

        tile.merge();

        assertEquals(8, tile.getValue());
    }

    @Test
    void shouldRejectNonPositiveTileValue() {
        assertThrows(IllegalArgumentException.class, () -> new Tile(0));
    }
}
