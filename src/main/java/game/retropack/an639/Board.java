package game.retropack.an639;

import game.retropack.an639.snake.Snake;

public interface Board {
    int getWidth();
    int getHeight();
    boolean[][] drawBoard();
    GameStates getGameState();
}
