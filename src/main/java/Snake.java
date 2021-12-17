import java.util.*;

public class Snake {
    Point head;
    Deque<Point> tail;
    Movement currentMovement;
    private Snake(Point head) {
        this.head = head;
        this.tail = new LinkedList<>();
        tail.addFirst(head);
        tail.add(new Point(head.getX() - 1, head.getY()));
        this.currentMovement = Movement.RIGHT;
    }

    public static Snake getFreshSnake(int boardWidth, int boardHeight) {
        Point head = new Point(boardWidth / 2, boardHeight / 2);
        return new Snake(head);
    }

    public Point getHead() {
        return head;
    }

    public void getNextSnake(Movement input, Point food) {
        this.currentMovement = getNextInput(input, currentMovement);
        Point newHead =
                switch (currentMovement) {
                    case UP -> new Point(head.getX(), head.getY() - 1);
                    case DOWN -> new Point(head.getX(), head.getY() + 1);
                    case LEFT -> new Point(head.getX() - 1, head.getY());
                    case RIGHT -> new Point(head.getX() + 1, head.getY());
                };
        updateSnake(newHead, food);
    }

    private void updateSnake(Point newHead, Point food) {
        if (tail.contains(newHead)) {
            throw new UnsupportedOperationException();
        }
        tail.addFirst(newHead);
        this.head = newHead;
        if (!newHead.equals(food)) {
            tail.removeLast();
        }
    }

    //TODO: get rid of static method somehow?
    private Movement getNextInput(Movement inputState, Movement currentState) {
        if (inputState == currentState.opposite() || inputState == currentState) {
            return currentState;
        }
        return inputState;
    }


    public Deque<Point> getSnakePoints() {
        return tail;
    }

    public Set<Point> getSnakePointsAsSet() {
        return new HashSet<>(tail);
    }

}