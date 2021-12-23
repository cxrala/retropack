package game.retropack.an639.pong;

import game.retropack.an639.Point;

import java.util.Arrays;

public class Ball {
    private int dx;
    private int dy;
    Point ballCoords;
    private final int boardWidth;
    private final int boardHeight;

    Ball(int boardWidth, int boardHeight) {
        this.ballCoords = new Point(boardWidth / 2, boardHeight / 2);
        dy = 0;
        dx = -1;

        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    public boolean nextBall(Paddle left, Paddle right) {
        Point newBall = new Point(ballCoords.getX() + dx, ballCoords.getY() + dy);
        if (newBall.getX() < 0) {
            return false;
        }
        if (newBall.getX() == 0) {
            if (Arrays.asList(left.getPaddlePoints()).contains(newBall)) {
                dx = -dx;
                dy = left.getCentre().getY() - newBall.getY();
                return true;
            }
        } else if (newBall.getX() == boardWidth - 1) {
            if (Arrays.asList(right.getPaddlePoints()).contains(newBall)) {
                dx = -dx;
                dy = left.getCentre().getY() - newBall.getY();
                return true;
            }
        }
        if (newBall.getY() <= 0 || newBall.getY() >= boardHeight - 1) {
            dy = -dy;
        }

        ballCoords.setX(ballCoords.getX() + dx);
        ballCoords.setY(ballCoords.getY() + dy);
        return true;
    }
}
