import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 * Game engine that handles the game logic and user interactions
 */
public class GameEngine {
    // Maximum number of attempts allowed per game
    private static final int MAX_ATTEMPTS = 7;

    private Scanner scanner;
    private FileManager fileManager;

    /**
     * Creates a new game engine instance
     * @param scanner For reading user input
     * @param fileManager For managing score persistence
     */
    public GameEngine(Scanner scanner, FileManager fileManager) {
        this.scanner = scanner;
        this.fileManager = fileManager;
    }

    /**
     * Main game loop that handles a single round of the game
     */
    public void playGame() {
        // Initialize game state
        int randomNumber = generateRandomNumber(1, 100);
        ArrayList<Integer> guessHistory = new ArrayList<>();
        int attempts = 0;
        boolean hasWon = false;

        System.out.println("\nI have generated a random number between 1 and 100. You have " 
                            + MAX_ATTEMPTS + " attempts to guess it.");

        // Keep accepting guesses until max attempts reached
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            int guess = validateIntegerInput();
            guessHistory.add(guess);
            attempts++;

            // Check if guess is correct and provide feedback
            if (guess == randomNumber) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                hasWon = true;
                break;
            } else if (guess > randomNumber) {
                System.out.println("Too High!");
            } else {
                System.out.println("Too Low!");
            }
        }

        // Game over, show result if player lost
        if (!hasWon) {
            System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
        }

        // Show game summary
        System.out.println("Your guesses were: " + guessHistory);

        // Save the game result
        fileManager.writeScoreToFile(hasWon, attempts, randomNumber);
    }

    /**
     * Generates a random number in the specified range
     * @param min Minimum value (inclusive)
     * @param max Maximum value (inclusive)
     * @return Random number between min and max
     */
    private int generateRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Gets and validates integer input from the user
     * Keeps prompting until valid input is received
     * @return Valid integer entered by user
     */
    private int validateIntegerInput() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid integer: ");
            }
        }
    }
}
