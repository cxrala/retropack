import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    /**Todo: CODE REFACTOR beep beep boop boop
     * Add win conditions (set of unoccupied points has size 0)
     * Add loss conditions instead of throwing exceptions ... state change for game?
     * Get rid of the array out of bounds exception and instead ... uhh.... well fuck
     * two options: make snake loop round board (sounds hard ngl)
     * or you can figure out how tf to do composite pattern while ... NVM METHOD OVERLOADING ok alls good
     * get rid of static methods (?)
     * Figure out how to compose multiple boards together
     * refactor so that it's relatively flexible to add a gui/some other input on top
     * sobs. can you decorate the snake so that it can move diagonally or is your code
     * too inflexible and shit to do that :thinking emoji: idk let's see truly how badly
     * designed this baby is
     * nyaaaaaaaaaaa
     * ok
     * thats all folks
     */

    public static void main(String[] args) {
        // todo: this is so sus this should really be in the Game class...(?)
        Scanner scanner = new Scanner(System.in);
        try {
            Game game = new Game(new TinyBoard());
            while (true) {
                game.drawBoard();
                System.out.println("Next move:");
                String input = scanner.nextLine();
                game.nextState(game.convertStringToMovement(input));
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            System.out.println("System.in was closed; exiting");
        }

    }
}
