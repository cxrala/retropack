import java.util.Objects;

public class Game {

    private Board board;

    Game(Board board) {
        this.board = board;
    }

    // TODO: make into map/enum
    public Movement convertStringToMovement(String input) {
        if (Objects.equals(input.toLowerCase(), "down")) {
            return Movement.DOWN;
        }
        if (Objects.equals(input.toLowerCase(), "up")) {
            return Movement.UP;
        }
        if (Objects.equals(input.toLowerCase(), "left")) {
            return Movement.LEFT;
        }
        if (Objects.equals(input.toLowerCase(), "right")) {
            return Movement.RIGHT;
        }
        return board.getSnake().getCurrentMovement();
    }

    public void drawBoard() {
        boolean[][] temp = board.drawBoard();
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                System.out.print(temp[i][j] ? "s " : "- ");
            }
            System.out.println();
        }
    }

    public void nextState(Movement input) {
        this.board = board.nextBoard(input);
    }

    public boolean isGameOver() {
        return board.gameOver();
    }
}