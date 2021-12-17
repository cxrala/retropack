import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // TODO: this is so sus this should really be in the Game class...(?)
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            while (true) {
                Game game = new Game(new TinyBoard());
                while (!game.isGameOver()) {
                    game.drawBoard();
                    System.out.println("Next move:");
                    String input = br.readLine();
                    game.nextState(game.convertStringToMovement(input));
                }
                System.out.println("how tf did you lose at snake");
                System.out.println("play again?");
                String input = br.readLine();
                if (!input.equalsIgnoreCase("yes")) {
                    break;
                }
            }
            System.out.println("smh compscicat does not like u");
        } catch(IllegalStateException | NoSuchElementException | IOException e) {
            System.out.println("System.in was closed; exiting");
        }

    }
}
