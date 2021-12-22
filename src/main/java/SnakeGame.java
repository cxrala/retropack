import java.util.HashMap;
import java.util.Map;

public class SnakeGame implements Game {

    private SnakeBoard snakeBoard;
    private final Map<String, Movement> movementMap;

    SnakeGame(SnakeBoard snakeBoard) {
        this.snakeBoard = snakeBoard;

        movementMap = new HashMap<>();

        movementMap.put("down", Movement.DOWN);
        movementMap.put("up", Movement.UP);
        movementMap.put("left", Movement.LEFT);
        movementMap.put("right", Movement.RIGHT);

    }

    public Movement convertStringToMovement(String input) {
        String filteredInput = input.toLowerCase();
        if (movementMap.containsKey(filteredInput)) {
            return movementMap.get(filteredInput);
        }
        return snakeBoard.getSnake().getCurrentMovement();
    }

    @Override
    public void draw() {
        boolean[][] temp = snakeBoard.drawBoard();
        for (int i = 0; i < snakeBoard.getWidth(); i++) {
            for (int j = 0; j < snakeBoard.getHeight(); j++) {
                System.out.print(temp[i][j] ? "s " : "- ");
            }
            System.out.println();
        }
    }

    public void updateState(Movement input) {
        this.snakeBoard = snakeBoard.nextBoard(input);
    }

    public GameStates getGameState() {
        return snakeBoard.getGameState();
    }
}