package game.retropack.an639.snake;

import game.retropack.an639.*;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TinySnakeBoard extends SnakeBoard {

    private final Snake snake;
    private final Set<Point> pointSet;
    private Point food;
    private GameStates gameState;

    public TinySnakeBoard() {
        snake = Snake.getFreshSnake(getWidth(), getHeight());
        food = new Point(getWidth() / 2 + 2, getHeight() / 2);
        pointSet = IntStream.range(0, getWidth())
                .mapToObj(i -> IntStream.range(0, getHeight()).mapToObj(j -> new Point(i, j)))
                .flatMap(UnaryOperator.identity())
                .collect(Collectors.toCollection(HashSet::new));
        gameState = GameStates.PLAYING;
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
    public SnakeBoard nextBoard(Movement2D input) {
        if (snake.hasCollided()) {
            gameState = GameStates.LOST;
        }
        snake.nextSnake(input, food);
        Point snakeHead = snake.getHead();
        if (snakeHead.getX() >= getWidth() | snakeHead.getY() >= getHeight() | snakeHead.getX() < 0 | snakeHead.getY() < 0) {
            gameState = GameStates.LOST;
        }
        if (snakeHead.equals(food)) {
            food = getNewFood();
        }
        return this;
    }

    private Point getNewFood() {
        Set<Point> unoccupiedPoints = new HashSet<>(pointSet);
        unoccupiedPoints.removeAll(new HashSet<>(snake.getSnakePoints()));

        if (unoccupiedPoints.size() == 0) {
            gameState = GameStates.WON;
        }
        int item = new Random().nextInt(unoccupiedPoints.size());
        int i = 0;
        for (Point point : unoccupiedPoints) {
            if (i++ == item) {
                return point;
            }
        }

        throw new NoSuchElementException();
    }


    @Override
    public boolean[][] drawBoard() {
        if (gameState == GameStates.PLAYING) {
            boolean[][] finalBoard = drawSnakeOnBoard(snake);
            finalBoard[food.getY()][food.getX()] = true;

            return finalBoard;
        }
        return new boolean[8][8];
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

    public GameStates getGameState() {
        return gameState;
    }

}
