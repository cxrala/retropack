import java.util.Objects;

public class Game {

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
        return Movement.UP;
    }

    private Board board;

    Game(Board board) {
        this.board = board;
    }

    public void drawBoard() {
        System.out.println(board.drawBoard());
    }

    public void nextState(Movement input) {
        this.board = board.nextBoard(input);
    }
}