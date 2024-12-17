import java.io.*;
import java.util.*;

/**
 * Handles reading and writing game scores to a file
 */
public class FileManager {
    // Name of file to store scores
    private String scoreFileName;

    /**
     * Creates a new FileManager that will use the specified file
     * @param scoreFileName Name of the score file to use
     */
    public FileManager(String scoreFileName) {
        this.scoreFileName = scoreFileName;
    }

    /**
     * Saves the game result to the score file
     * Includes whether player won, number of attempts, target number and timestamp
     * 
     * @param hasWon Whether the player won the game
     * @param attempts Number of guesses made
     * @param number The target number to guess
     */
    public void writeScoreToFile(boolean hasWon, int attempts, int number) {
        // Use try-with-resources to auto-close the writers
        try (FileWriter fw = new FileWriter(scoreFileName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            
            String result = hasWon ? "Win" : "Loss";
            String timeStamp = new Date().toString();
            out.println("Result: " + result + ", Number: " + number + ", Attempts: " + attempts + ", Date: " + timeStamp);
        } catch (IOException e) {
            System.err.println("Error writing to score file: " + e.getMessage());
        }
    }

    /**
     * Reads and displays all past game scores from the file
     * Shows a message if no scores exist yet
     */
    public void readScoresFromFile() {
        // Check if score file exists
        File scoreFile = new File(scoreFileName);
        if (!scoreFile.exists()) {
            System.out.println("No past scores available.");
            return;
        }

        // Read and print each line from the file
        try (Scanner fileScanner = new Scanner(scoreFile)) {
            boolean hasLines = false;
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
                hasLines = true;
            }
            if (!hasLines) {
                System.out.println("No past scores available.");
            }
        } catch (IOException e) {
            System.err.println("Error reading score file: " + e.getMessage());
        }
    }
}
