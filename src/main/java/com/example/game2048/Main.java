package com.example.game2048;

import com.example.game2048.grid.Grid;
import com.example.game2048.grid.Tile;

import java.util.Arrays;

/**
 * Deterministic acceptance runner for the challenge.
 * It validates returned values and resulting board state; exceptions alone do not mean PASS.
 */
public final class Main {
    private static int passed;
    private static int failed;

    private Main() {}

    public static void main(String[] args) {
        System.out.println("=== 2048 Java SWE Challenge Runner ===");
        run("Game starts with two tiles", Main::initializationCase);
        run("Two equal tiles merge when moving left", Main::mergeCase);
        run("Move right shifts tiles to the edge", Main::moveRightCase);
        run("Score increases by merged tile value", Main::scoreCase);
        run("Restart clears the board and resets score", Main::restartCase);

        System.out.println("----------------------------------------");
        System.out.printf("Result: %d/%d passed%n", passed, passed + failed);
        System.out.println("Status: " + (failed == 0 ? "PASSED" : "FAILED"));
        if (failed > 0) System.exit(1);
    }

    private static boolean initializationCase() {
        Game game = new Game();
        game.start();
        return game.getState() == GameState.RUNNING && countTiles(game.getGrid()) == 2;
    }

    private static boolean mergeCase() {
        Game game = new Game();
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));
        game.getGrid().setTile(0, 1, new Tile(2));
        game.move(Direction.LEFT);
        return row(game.getGrid(), 0).equals(Arrays.asList(4, 0, 0, 0));
    }

    private static boolean moveRightCase() {
        Game game = new Game();
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));
        game.getGrid().setTile(0, 1, new Tile(4));
        game.move(Direction.RIGHT);
        return row(game.getGrid(), 0).equals(Arrays.asList(0, 0, 2, 4));
    }

    private static boolean scoreCase() {
        Game game = new Game();
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));
        game.getGrid().setTile(0, 1, new Tile(2));
        game.move(Direction.LEFT);
        return game.getScore() == 4;
    }

    private static boolean restartCase() {
        Game game = new Game();
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));
        game.move(Direction.RIGHT);
        game.restart();
        return game.getState() == GameState.RUNNING
                && game.getScore() == 0
                && countTiles(game.getGrid()) == 2;
    }

    private static void run(String name, Case scenario) {
        try {
            boolean result = scenario.execute();
            if (result) {
                passed++;
                System.out.println("[PASS] " + name);
            } else {
                failed++;
                System.out.println("[FAIL] " + name + " - expected state/value was not reached");
            }
        } catch (RuntimeException ex) {
            failed++;
            System.out.println("[FAIL] " + name + " - execution error: " + ex.getMessage());
        }
    }

    private static int countTiles(Grid grid) {
        int count = 0;
        for (int r = 0; r < grid.getSize(); r++) {
            for (int c = 0; c < grid.getSize(); c++) {
                if (grid.getTile(r, c) != null) count++;
            }
        }
        return count;
    }

    private static java.util.List<Integer> row(Grid grid, int row) {
        java.util.List<Integer> values = new java.util.ArrayList<>();
        for (int c = 0; c < grid.getSize(); c++) {
            Tile tile = grid.getTile(row, c);
            values.add(tile == null ? 0 : tile.getValue());
        }
        return values;
    }

    @FunctionalInterface
    private interface Case { boolean execute(); }
}
