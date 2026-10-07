package com.example.game2048.service;

import com.example.game2048.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TileGeneratorEdgeCaseTest {

    @Test
    void shouldCreateMostlyTwos() {
        TileGenerator generator = new TileGenerator(new Random(7));
        int twos = 0;
        for (int i = 0; i < 1000; i++) {
            if (generator.createTile().getValue() == 2) {
                twos++;
            }
        }
        assertTrue(twos > 800 && twos < 980);
    }

    @Test
    void shouldRejectPickingFromNoPositions() {
        TileGenerator generator = new TileGenerator(new Random(7));

        assertThrows(IllegalArgumentException.class, () -> generator.pickPosition(new ArrayList<Position>()));
    }
}