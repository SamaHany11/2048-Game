package com.example.game2048;

import com.example.game2048.exception.InvalidMoveException;
import com.example.game2048.grid.Grid;
import com.example.game2048.service.MoveService;
import com.example.game2048.service.TileGenerator;

public class Game {

    private static final int STARTING_TILES = 2;

    private final Grid grid;
    private final MoveService moveService;
    private final TileGenerator tileGenerator;

    private GameState state;
    private int score;

    public Game() {
        this(Grid.DEFAULT_SIZE, new TileGenerator());
    }

    public Game(int gridSize) {
        this(gridSize, new TileGenerator());
    }

    public Game(int gridSize, TileGenerator tileGenerator) {
        this.grid = new Grid(gridSize);
        this.moveService = new MoveService(grid);
        this.tileGenerator = tileGenerator;
        this.state = GameState.NEW;
    }

    public void start() {
        grid.clear();
        score = 0;
        for (int i = 0; i < STARTING_TILES; i++) {
            addRandomTile();
        }
        state = GameState.RUNNING;
    }

    public boolean move(Direction direction) {
        if (state != GameState.RUNNING) {
            throw new InvalidMoveException("Cannot move while the game is " + state);
        }

        boolean changed = moveService.move(direction);
        if (!changed) {
            return false;
        }

        score += moveService.getScoreOfLastMove();
        addRandomTile();
        if (isGameOver()) {
            state = GameState.GAME_OVER;
        }
        return true;
    }

    public void restart() {
        start();
    }

    public GameState getState() {
        return state;
    }

    public int getScore() {
        return score;
    }

    public Grid getGrid() {
        return grid;
    }

    private void addRandomTile() {
        Position position = tileGenerator.pickPosition(grid.getEmptyPositions());
        grid.setTile(position.row(), position.column(), tileGenerator.createTile());
    }

    private boolean isGameOver() {
        return grid.isFull() && !grid.hasEqualNeighbours();
    }
}