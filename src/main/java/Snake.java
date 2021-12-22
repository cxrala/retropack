import java.util.Deque;
import java.util.LinkedList;

public class Snake {
    private Point head;
    private final Deque<Point> tail;
    private Movement currentMovement;
    private boolean hasCollided;

    private Snake(Point head) {
        this.head = head;
        tail = new LinkedList<>();
        tail.add(new Point(head.getX() - 1, head.getY()));
        tail.addFirst(head);
        currentMovement = Movement.RIGHT;
    }

    public static Snake getFreshSnake(int boardWidth, int boardHeight) {
        Point head = new Point(boardWidth / 2, boardHeight / 2);
        return new Snake(head);
    }

    public void nextSnake(Movement input, Point food) {
        currentMovement = getNextInput(input, currentMovement);
        Point newHead = Movement.getNewHead(currentMovement, head);
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

    private Movement getNextInput(Movement inputState, Movement currentState) {
        if (inputState == currentState.opposite() || inputState == currentState) {
            return currentState;
        }
        return inputState;
    }


    public Deque<Point> getSnakePoints() {
        return tail;
    }

    public Movement getCurrentMovement() {
        return currentMovement;
    }

    public Point getHead() {
        return head;
    }

    public boolean hasCollided() {
        return hasCollided;
    }
}