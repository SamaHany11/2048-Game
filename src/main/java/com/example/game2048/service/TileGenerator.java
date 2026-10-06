package com.example.game2048.service;

import com.example.game2048.grid.Tile;

import java.util.Random;

public class TileGenerator {

    private final Random random;

    public TileGenerator() {
        this(new Random());
    }

    public TileGenerator(Random random) {
        this.random = random;
    }

    public Tile createTile() {
        // TODO: Generate 2 with 90% probability and 4 with 10% probability.
        return null;
    }
}
