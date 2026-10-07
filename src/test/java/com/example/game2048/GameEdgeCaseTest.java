package com.example.game2048;

import com.example.game2048.exception.InvalidMoveException;
import com.example.game2048.grid.Tile;
import com.example.game2048.service.TileGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GameEdgeCaseTest {

    /** A Random with fixed answers: always makes a "4" tile and picks the first empty cell. */
    private static class AlwaysFourRandom extends Random {
        @Override
        public int nextInt(int bound) {
            return bound == 100 ? 99 : 0;
        }
    }

    private int countTiles(Game game) {
        int total = game.getGrid().getSize() * game.getGrid().getSize();
        return total - game.getGrid().getEmptyPositions().size();
    }

    /**
     * 2x2 game that ends after one LEFT move:
     *   4 2        4 2
     *   _ 8  -->   8 4   (new tile is a 4, nothing can merge)
     */
    private Game gameThatEndsAfterOneMove() {
        Game game = new Game(2, new TileGenerator(new AlwaysFourRandom()));
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(4));
        game.getGrid().setTile(0, 1, new Tile(2));
        game.getGrid().setTile(1, 1, new Tile(8));
        return game;
    }

    @Test
    void shouldNotMoveBeforeTheGameStarts() {
        Game game = new Game();

        assertThrows(InvalidMoveException.class, () -> game.move(Direction.LEFT));
    }

    @Test
    void noOpMoveShouldNotAddTileOrChangeScore() {
        Game game = new Game(4, new TileGenerator(new Random(1234)));
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));

        assertFalse(game.move(Direction.LEFT));

        assertEquals(1, countTiles(game));
        assertEquals(0, game.getScore());
    }

    @Test
    void scoreShouldAddUpAcrossMoves() {
        Game game = new Game(4, new TileGenerator(new Random(1234)));
        game.start();
        game.getGrid().clear();
        game.getGrid().setTile(0, 0, new Tile(2));
        game.getGrid().setTile(0, 1, new Tile(2));
        game.move(Direction.LEFT);                    // +4

        game.getGrid().clear();
        game.getGrid().setTile(3, 0, new Tile(8));
        game.getGrid().setTile(3, 1, new Tile(8));
        game.move(Direction.LEFT);                    // +16

        assertEquals(20, game.getScore());
    }

    @Test
    void gameShouldBeOverWhenBoardIsFullAndNothingCanMerge() {
        Game game = gameThatEndsAfterOneMove();

        assertTrue(game.move(Direction.LEFT));

        assertEquals(GameState.GAME_OVER, game.getState());
    }

    @Test
    void shouldRejectMovesAfterGameOver() {
        Game game = gameThatEndsAfterOneMove();
        game.move(Direction.LEFT);

        assertThrows(InvalidMoveException.class, () -> game.move(Direction.RIGHT));
    }

    @Test
    void restartShouldWorkAfterGameOver() {
        Game game = gameThatEndsAfterOneMove();
        game.move(Direction.LEFT);

        game.restart();

        assertEquals(GameState.RUNNING, game.getState());
        assertEquals(0, game.getScore());
        assertEquals(2, countTiles(game));
    }
}