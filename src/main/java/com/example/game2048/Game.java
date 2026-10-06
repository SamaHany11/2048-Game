package com.example.game2048;

import com.example.game2048.grid.Grid;
import com.example.game2048.service.MoveService;
import com.example.game2048.service.TileGenerator;

public class Game {

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
        // TODO: Start a fresh game with two tiles.
    }

    public boolean move(Direction direction) {
        // TODO: Reject moves when appropriate, move the board, update score,
        // spawn a tile after a successful move and update game state.
        return false;
    }

    public void restart() {
        // TODO
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

    // TODO: The implementation should determine whether the game has ended.
    private boolean isGameOver() {
        return false;
    }
}
