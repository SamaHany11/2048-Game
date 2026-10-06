package com.example.game2048.grid;

public class Grid {

    public static final int DEFAULT_SIZE = 4;

    private final int size;
    private final Tile[][] tiles;

    public Grid() {
        this(DEFAULT_SIZE);
    }

    public Grid(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Grid size must be at least 2");
        }
        this.size = size;
        this.tiles = new Tile[size][size];
    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int row, int column) {
        // TODO: Validate coordinates and return the tile.
        return null;
    }

    public void setTile(int row, int column, Tile tile) {
        // TODO: Validate coordinates and update the cell.
    }

    public boolean isEmpty(int row, int column) {
        // TODO
        return false;
    }

    public boolean isFull() {
        // TODO
        return false;
    }

    public void clear() {
        // TODO
    }
}
