public interface SnakeBoard {
    int getWidth();

    int getHeight();

    boolean[][] drawBoard();

    SnakeBoard nextBoard(Movement input);

    Snake getSnake();

    GameStates getGameState();
}
