package com.example.game2048.service;

import com.example.game2048.Direction;
import com.example.game2048.grid.Grid;
import com.example.game2048.grid.Tile;

public class MoveService {

    private final Grid grid;

    public MoveService(Grid grid) {
        this.grid = grid;
    }

    /**
     * Moves the board in the requested direction.
     *
     * @return true when the board changed, false for a no-op move.
     */
    public boolean move(Direction direction) {
        // TODO: Implement movement, shifting and merging.
        return false;
    }

    private boolean moveLeft() {
        // TODO
        return false;
    }

    private boolean moveRight() {
        // TODO
        return false;
    }

    private boolean moveUp() {
        // TODO
        return false;
    }

    private boolean moveDown() {
        // TODO
        return false;
    }

    // TODO: Decide whether this responsibility belongs here and implement it.
    private boolean mergeRow(int row) {
        return false;
    }
}
