package game.retropack.an639.snake;

import game.retropack.an639.*;
import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private Point head;
    private final Deque<Point> tail;
    private Movement2D currentMovement2D;
    private boolean hasCollided;

    private Snake(Point head) {
        this.head = head;
        tail = new LinkedList<>();
        tail.add(new Point(head.getX() - 1, head.getY()));
        tail.addFirst(head);
        currentMovement2D = Movement2D.RIGHT;
    }

    public static Snake getFreshSnake(int boardWidth, int boardHeight) {
        Point head = new Point(boardWidth / 2, boardHeight / 2);
        return new Snake(head);
    }

    public void nextSnake(Movement2D input, Point food) {
        currentMovement2D = getNextInput(input, currentMovement2D);
        Point newHead = Movement2D.nextPoint(currentMovement2D, head);
        updateSnake(newHead, food);
    }

    private void updateSnake(Point newHead, Point food) {
        if (tail.contains(newHead)) {
            hasCollided = true;
            return;
        }
        tail.addFirst(newHead);
        head = newHead;
        if (!newHead.equals(food)) {
            tail.removeLast();
        }
    }

    private Movement2D getNextInput(Movement2D inputState, Movement2D currentState) {
        if (inputState == currentState.opposite() || inputState == currentState) {
            return currentState;
        }
        return inputState;
    }


    public Deque<Point> getSnakePoints() {
        return tail;
    }

    public Movement2D getCurrentMovement() {
        return currentMovement2D;
    }

    public Point getHead() {
        return head;
    }

    public boolean hasCollided() {
        return hasCollided;
    }
}