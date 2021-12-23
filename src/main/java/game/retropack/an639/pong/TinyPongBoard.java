package game.retropack.an639.pong;

import game.retropack.an639.GameStates;
import game.retropack.an639.Point;

public class TinyPongBoard extends PongBoard {
    @Override
    public int getWidth() {
        return 8;
    }

    @Override
    public int getHeight() {
        return 8;
    }

    @Override
    public boolean[][] drawBoard() {
        return new boolean[0][];
    }

    @Override
    public GameStates getGameState() {
        return null;
    }

    @Override
    Point getNextBall() {
        return null;
    }
}
