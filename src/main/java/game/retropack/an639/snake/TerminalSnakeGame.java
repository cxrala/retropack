package game.retropack.an639.snake;

import game.retropack.an639.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class TerminalSnakeGame implements Game {

    private SnakeBoard snakeBoard;
    private final Map<String, Movement2D> movementMap;

    public TerminalSnakeGame(SnakeBoard snakeBoard) {
        this.snakeBoard = snakeBoard;

        movementMap = new HashMap<>();

        movementMap.put("down", Movement2D.DOWN);
        movementMap.put("up", Movement2D.UP);
        movementMap.put("left", Movement2D.LEFT);
        movementMap.put("right", Movement2D.RIGHT);

    }

    public Movement2D convertStringToMovement(String input) {
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

    @Override
    public void play() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                while (getGameState() == GameStates.PLAYING) {
                    draw();
                    System.out.println("Next move: ");
                    String input = br.readLine();
                    updateState(convertStringToMovement(input));
                }
                if (getGameState() == GameStates.LOST) {
                    System.out.println("You lost.");
                } else {
                    System.out.println("You won!");
                }
                System.out.println("Play again?");
                String input = br.readLine();
                if (!input.equalsIgnoreCase("yes")) {
                    break;
                }
            }
            System.out.println("You have disappointed your family."); // this will change
        } catch(IllegalStateException | NoSuchElementException | IOException e) {
            System.out.println("System.in was closed; exiting"); // this will change
        }
    }

    public void updateState(Movement2D input) {
        this.snakeBoard = snakeBoard.nextBoard(input);
    }

    public GameStates getGameState() {
        return snakeBoard.getGameState();
    }
}