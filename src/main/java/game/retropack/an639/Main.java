package game.retropack.an639;

import game.retropack.an639.snake.*;

public class Main {
    public static void main(String[] args) {
        TerminalSnakeGame snakeGame = new TerminalSnakeGame(new TinySnakeBoard());
        snakeGame.play();
    }
}
