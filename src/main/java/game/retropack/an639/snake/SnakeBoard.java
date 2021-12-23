package game.retropack.an639.snake;
import game.retropack.an639.*;
abstract class SnakeBoard implements Board {
    abstract Snake getSnake();
    abstract SnakeBoard nextBoard(Movement2D input);

}
