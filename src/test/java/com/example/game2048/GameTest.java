package com.example.game2048;

import com.example.game2048.grid.Tile;
import com.example.game2048.service.TileGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void newGameShouldStartInNewState() {
        Game game = new Game();

        assertEquals(GameState.NEW, game.getState());
        assertEquals(0, game.getScore());
    }

    @Test
    void startingGameShouldCreateTwoTiles() {
        Game game = new Game(4, new TileGenerator(new Random(1234)));

        game.start();

        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(2, countTiles(game));
    }

    @Test
    void successfulMoveShouldIncreaseBoardActivity() {
        Game game = new Game(4, new TileGenerator(new Random(1234)));
        game.start();

        boolean moved = false;
        for (Direction direction : Direction.values()) {
            if (game.move(direction)) {
                moved = true;
                break;
            }
        }

        assertTrue(moved);
        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(3, countTiles(game));
    }

    @Test
    void restartShouldClearScoreAndCreateFreshGame() {
        Game game = new Game(4, new TileGenerator(new Random(1234)));
        game.start();

        game.restart();

        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(0, game.getScore());
        assertEquals(2, countTiles(game));
    }

    private int countTiles(Game game) {
        int count = 0;
        for (int row = 0; row < game.getGrid().getSize(); row++) {
            for (int column = 0; column < game.getGrid().getSize(); column++) {
                Tile tile = game.getGrid().getTile(row, column);
                if (tile != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
