package com.example.game2048.grid;

import com.example.game2048.Position;

import java.util.ArrayList;
import java.util.List;

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

    public boolean isInside(int row, int column) {
        return row >= 0 && row < size && column >= 0 && column < size;
    }

    public Tile getTile(int row, int column) {
        checkInside(row, column);
        return tiles[row][column];
    }

    public void setTile(int row, int column, Tile tile) {
        checkInside(row, column);
        tiles[row][column] = tile;
    }

    public boolean isEmpty(int row, int column) {
        return getTile(row, column) == null;
    }

    public boolean isFull() {
        return getEmptyPositions().isEmpty();
    }

    public void clear() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                tiles[row][column] = null;
            }
        }
    }

    public List<Position> getEmptyPositions() {
        List<Position> empty = new ArrayList<>();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (tiles[row][column] == null) {
                    empty.add(new Position(row, column));
                }
            }
        }
        return empty;
    }

    public boolean hasEqualNeighbours() {
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (equalsNeighbour(row, column, row, column + 1)
                        || equalsNeighbour(row, column, row + 1, column)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean equalsNeighbour(int row, int column, int otherRow, int otherColumn) {
        if (!isInside(otherRow, otherColumn)) {
            return false;
        }
        Tile first = tiles[row][column];
        Tile second = tiles[otherRow][otherColumn];
        return first != null && first.equals(second);
    }

    private void checkInside(int row, int column) {
        if (!isInside(row, column)) {
            throw new IndexOutOfBoundsException(
                    "Cell (" + row + ", " + column + ") is outside a " + size + "x" + size + " grid");
        }
    }
}