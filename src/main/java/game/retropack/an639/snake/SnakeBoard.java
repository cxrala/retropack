package game.retropack.an639.snake;

import game.retropack.an639.*;
public interface SnakeBoard {
    int getWidth();

    int getHeight();

    boolean[][] drawBoard();

    SnakeBoard nextBoard(Movement input);

    Snake getSnake();

    GameStates getGameState();
}
