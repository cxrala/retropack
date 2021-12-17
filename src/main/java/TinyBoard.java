import java.util.Deque;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TinyBoard implements Board {
    private final Snake snake;
    private final Set<Point> pointSet;
    private Point food;
    private boolean isOver;

    TinyBoard() {
        snake = Snake.getFreshSnake(getWidth(), getHeight());
        food = new Point(getWidth() / 2 + 2, getHeight() / 2);
        pointSet = IntStream.range(0, getWidth())
                .mapToObj(i -> IntStream.range(0, getHeight()).mapToObj(j -> new Point(i, j)))
                .flatMap(UnaryOperator.identity())
                .collect(Collectors.toCollection(HashSet::new));
    }

    private boolean[][] drawSnakeOnBoard(Snake snake) {
        boolean[][] tempBoard = new boolean[getWidth()][getHeight()];
        Deque<Point> snakePoints = snake.getSnakePoints();

        //TODO: streams
        for (Point point : snakePoints) {
            tempBoard[point.getY()][point.getX()] = true;
        }
        return tempBoard;
    }

    @Override
    public Board nextBoard(Movement input) {
        if (snake.hasCollided()) {
            isOver = true;
        }
        snake.getNextSnake(input, food);
        if (snake.getHead().equals(food)) {
            food = getNewFood();
        }
        return this;
    }

    private Point getNewFood() {
        //TODO: if unoccupiedPoints = 0 then win
        Set<Point> unoccupiedPoints = new HashSet<>(pointSet);
        unoccupiedPoints.removeAll(new HashSet<>(snake.getSnakePoints()));

        int item = new Random().nextInt(unoccupiedPoints.size());
        int i = 0;
        for (Point point : unoccupiedPoints) {
            if (i++ == item) {
                return point;
            }
        }

        // todo: they win because the unoccupiedPoints set has a size 0
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean[][] drawBoard() {
        boolean[][] finalBoard = drawSnakeOnBoard(snake);
        finalBoard[food.getY()][food.getX()] = true;

        return finalBoard;
    }

    @Override
    public int getWidth() {
        return 8;
    }

    @Override
    public int getHeight() {
        return 8;
    }

    public Snake getSnake() {
        return snake;
    }

    @Override
    public boolean gameOver() {
        return isOver;
    }

}
