package com.example.game2048.grid;

import java.util.Objects;

public class Tile {

    private int value;

    public Tile(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Tile value must be positive");
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void merge() {
        // TODO: Double the tile value.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile tile)) return false;
        return value == tile.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
