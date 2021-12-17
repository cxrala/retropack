public interface Board {
    int getWidth();

    int getHeight();

    boolean[][] drawBoard();

    Board nextBoard(Movement input);

    Snake getSnake();

    boolean gameOver();
}
