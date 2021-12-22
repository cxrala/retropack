import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                SnakeGame snakeGame = new SnakeGame(new TinySnakeBoard());

                while (snakeGame.getGameState() == GameStates.PLAYING) {
                    snakeGame.draw();
                    System.out.println("Next move: ");
                    String input = br.readLine(); // this will change
                    snakeGame.updateState(snakeGame.convertStringToMovement(input)); // this will change (thing inside the bracket)
                }
                if (snakeGame.getGameState() == GameStates.LOST) {
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
}
