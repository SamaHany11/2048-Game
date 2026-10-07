package com.example.game2048.service;

import com.example.game2048.Direction;
import com.example.game2048.grid.Grid;
import com.example.game2048.grid.Tile;

public class MoveService {

    private final Grid grid;
    private int scoreOfLastMove;

    public MoveService(Grid grid) {
        this.grid = grid;
    }

    public boolean move(Direction direction) {
        scoreOfLastMove = 0;
        boolean[][] alreadyMerged = new boolean[grid.getSize()][grid.getSize()];
        boolean changed = false;

        for (int row : visitOrder(direction.getRowStep())) {
            for (int column : visitOrder(direction.getColumnStep())) {
                if (slideTile(row, column, direction, alreadyMerged)) {
                    changed = true;
                }
            }
        }
        return changed;
    }

    public int getScoreOfLastMove() {
        return scoreOfLastMove;
    }

    private int[] visitOrder(int step) {
        int size = grid.getSize();
        int[] order = new int[size];
        for (int i = 0; i < size; i++) {
            order[i] = (step > 0) ? size - 1 - i : i;
        }
        return order;
    }

    private boolean slideTile(int row, int column, Direction direction, boolean[][] alreadyMerged) {
        Tile tile = grid.getTile(row, column);
        if (tile == null) {
            return false;
        }

        int currentRow = row;
        int currentColumn = column;

        while (true) {
            int nextRow = currentRow + direction.getRowStep();
            int nextColumn = currentColumn + direction.getColumnStep();

            if (!grid.isInside(nextRow, nextColumn)) {
                break;
            }
            if (grid.isEmpty(nextRow, nextColumn)) {
                currentRow = nextRow;
                currentColumn = nextColumn;
            } else if (canMerge(tile, nextRow, nextColumn, alreadyMerged)) {
                mergeInto(nextRow, nextColumn, alreadyMerged);
                grid.setTile(row, column, null);
                return true;
            } else {
                break;
            }
        }

        if (currentRow == row && currentColumn == column) {
            return false;
        }
        grid.setTile(currentRow, currentColumn, tile);
        grid.setTile(row, column, null);
        return true;
    }

    private boolean canMerge(Tile moving, int targetRow, int targetColumn, boolean[][] alreadyMerged) {
        Tile target = grid.getTile(targetRow, targetColumn);
        return !alreadyMerged[targetRow][targetColumn] && target.equals(moving);
    }

    private void mergeInto(int targetRow, int targetColumn, boolean[][] alreadyMerged) {
        Tile target = grid.getTile(targetRow, targetColumn);
        target.merge();
        alreadyMerged[targetRow][targetColumn] = true;
        scoreOfLastMove += target.getValue();
    }
}