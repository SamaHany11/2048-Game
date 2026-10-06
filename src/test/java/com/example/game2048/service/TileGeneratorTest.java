package com.example.game2048.service;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TileGeneratorTest {

    @Test
    void generatedTileShouldBeTwoOrFour() {
        TileGenerator generator = new TileGenerator(new Random(1234));

        for (int i = 0; i < 100; i++) {
            int value = generator.createTile().getValue();
            assertTrue(value == 2 || value == 4);
        }
    }
}
