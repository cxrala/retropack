import java.util.*;

public class TinyBoard implements Board {
    private Snake snake;
    private Set<Point> pointSet;
    private Point food;

    TinyBoard() {
        this.snake = Snake.getFreshSnake(getWidth(), getHeight());
        this.pointSet = new HashSet<>();
        this.food = new Point(getWidth() / 2 + 2, getHeight() / 2);

        //Todo: change to streams? Not rlly sure how to...
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                pointSet.add(new Point(i, j));
            }
        }
    }

    private boolean[][] drawSnakeOnBoard(Snake snake) {
        boolean[][] tempBoard = new boolean[getWidth()][getHeight()];
        Deque<Point> snakePoints = snake.getSnakePoints();
        for (Point point : snakePoints) {
            tempBoard[point.getY()][point.getX()] = true;
        }
        return tempBoard;
    }

    @Override
    public Board nextBoard(Movement input) {
        snake.getNextSnake(input, food);
        if (snake.getHead().equals(food)) {

            //TODO: if unoccupiedPoints = 0 then win
            this.food = getNewFood();

        }
        return this;
    }

    private Point getNewFood() {
        Set<Point> unoccupiedPoints = new HashSet<>(pointSet);
        unoccupiedPoints.removeAll(snake.getSnakePointsAsSet());

        int size = unoccupiedPoints.size();
        int item = new Random().nextInt(size);
        int i = 0;
        for (Point point : unoccupiedPoints) {
            if (i == item) {
                return point;
            }
            i++;
        }
        throw new UnsupportedOperationException();
    }

    @Override
    public int getWidth() {
        return 8;
    }

    @Override
    public int getHeight() {
        return 8;
    }


    @Override
    public String drawBoard() {
        boolean[][] finalBoard = drawSnakeOnBoard(snake);
        finalBoard[food.getY()][food.getX()] = true;

        return Arrays.deepToString(finalBoard).replace("], ", "]\n")
                .replace("[[", "[")
                .replace("]]", "]")
                .replace("false,", String.format("%-" + 1 + "s", "-"))
                .replace("false", String.format("%-" + 1 + "s", "-"))
                .replace("true, ", String.format("%-" + 2 + "d", 1))
                .replace("true", String.format("%-" + 1 + "d", 1));
    }

}
