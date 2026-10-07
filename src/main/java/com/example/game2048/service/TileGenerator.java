package com.example.game2048.service;

import com.example.game2048.Position;
import com.example.game2048.grid.Tile;

import java.util.List;
import java.util.Random;

public class TileGenerator {

    private static final int PERCENT = 100;
    private static final int CHANCE_OF_LOW_TILE = 90;
    private static final int LOW_TILE_VALUE = 2;
    private static final int HIGH_TILE_VALUE = 4;

    private final Random random;

    public TileGenerator() {
        this(new Random());
    }

    public TileGenerator(Random random) {
        this.random = random;
    }

    public Tile createTile() {
        if (random.nextInt(PERCENT) < CHANCE_OF_LOW_TILE) {
            return new Tile(LOW_TILE_VALUE);
        }
        return new Tile(HIGH_TILE_VALUE);
    }

    public Position pickPosition(List<Position> emptyPositions) {
        if (emptyPositions.isEmpty()) {
            throw new IllegalArgumentException("There is no empty cell to choose from");
        }
        return emptyPositions.get(random.nextInt(emptyPositions.size()));
    }
}