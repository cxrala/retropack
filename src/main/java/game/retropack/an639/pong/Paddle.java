package game.retropack.an639.pong;

import game.retropack.an639.Movement2D;
import game.retropack.an639.Point;

public class Paddle {
    private final Point[] paddlePoints;
    private final Point centre;

    //TODO: check for size safety
    private Paddle(Point centre, int size) {
        this.paddlePoints = new Point[2 * size + 1];
        int dy = -size;
        for (int i = 0; i < 2 * size + 1; i++) {
            paddlePoints[i] = new Point(centre.getX(), centre.getY() + dy);
            dy++;
        }
        this.centre = centre;
    }

    public Paddle initialisePaddle(boolean isLeft, int boardHeight, int boardWidth) {
        return new Paddle((isLeft ?
                (new Point(0, boardHeight / 2)) :
                (new Point(boardWidth - 1, boardHeight / 2))),
                1);
    }

    // TODO: remember in board to check if paddle will be out of board.
    public void updatePaddle(Movement2D movement) {
        // illegal states
        if (movement == Movement2D.LEFT || movement == Movement2D.RIGHT) {
            return;
        }
        int dy = switch (movement) {
            case UP -> 1;
            case DOWN -> -1;
            default -> 0;
        };
        for (int i = 0; i < paddlePoints.length; i++) {
            paddlePoints[i] = new Point(paddlePoints[i].getX(), paddlePoints[i].getY() + dy);
        }
        centre.setY(centre.getY() + dy);
    }

    public void updatePaddle() {
        // doesn't move so no changes to paddle object
    }

    public Point[] getPaddlePoints() {
        return paddlePoints;
    }

    public Point getCentre() {
        return centre;
    }
}
