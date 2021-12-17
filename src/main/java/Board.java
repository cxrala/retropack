public interface Board {
    int getWidth();
    int getHeight();
    String drawBoard();
    Board nextBoard(Movement input);
}
