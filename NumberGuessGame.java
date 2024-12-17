import java.util.Scanner;

/**
 * Main class for the Number Guessing Game.
 */
public class NumberGuessGame {
    public static void main(String[] args) {
        // Display welcome message
        System.out.println("Welcome to the Number Guessing Game!");

        // Initialize file manager and display previous scores
        FileManager fileManager = new FileManager("scores.txt");
        System.out.println("\n=== Past Scores ===");
        fileManager.readScoresFromFile();

        // Set up scanner and game engine
        Scanner scanner = new Scanner(System.in);
        GameEngine gameEngine = new GameEngine(scanner, fileManager);

        // Main game loop, keep playing until user chooses to quit
        boolean playAgain;
        do {
            gameEngine.playGame();
            playAgain = askToPlayAgain(scanner);
        } while (playAgain);

        // Clean up and exit the game
        System.out.println("Thank you for playing my game! Goodbye.");
        scanner.close();
    }

    /**
     * Ask the user if they want to play another round.
     * 
     * @param scanner
     * @return
     */
    private static boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Do you want to play again? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("y");
    }
}
