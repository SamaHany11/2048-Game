package com.example.game2048;

public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int rowStep;
    private final int columnStep;

    Direction(int rowStep, int columnStep) {
        this.rowStep = rowStep;
        this.columnStep = columnStep;
    }

    public int getRowStep() {
        return rowStep;
    }

    public int getColumnStep() {
        return columnStep;
    }
}