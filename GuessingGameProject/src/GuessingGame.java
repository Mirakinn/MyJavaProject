import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    private String playerName;
    private int secretNumber;
    private int attemptsUsed;
    private final int MAX_ATTEMPTS = 10;
    private Scanner scanner;

    public GuessingGame() {
        this.scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("========================================");
        System.out.println("WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.print("Enter your name: ");
        this.playerName = scanner.nextLine();

        boolean playAgain = true;
        while (playAgain) {
            runGameLoop();
            displayStats();
            
            System.out.print("Would you like to play again, " + playerName + "? (Y/N): ");
            String response = scanner.next();
            scanner.nextLine(); 
            playAgain = response.equalsIgnoreCase("y");
        }

        System.out.println("========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    private void runGameLoop() {
        Random rand = new Random();
        this.secretNumber = rand.nextInt(100) + 1; 
        this.attemptsUsed = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("========================================");
        System.out.println("WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hello, " + playerName + "!");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.");
        System.out.println("Let's begin!");
        System.out.println("========================================");

        while (attemptsUsed < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            attemptsUsed++;
            System.out.println("--- Attempt #" + attemptsUsed + " ---");
            int guess = getValidGuess();

            if (guess == secretNumber) {
                System.out.println("Congratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attemptsUsed + " attempts!");
                hasGuessedCorrectly = true;
            } else if (guess < secretNumber) {
                System.out.println("Too low! Try a higher number."); 
            } else {
                System.out.println("Too high! Try a lower number."); 
            }
        }

        if (!hasGuessedCorrectly) {
            System.out.println("--- Attempt #11 ---");
            System.out.println("GAME OVER!");
            System.out.println("You've used all " + MAX_ATTEMPTS + " attempts.");
            System.out.println("The secret number was " + secretNumber + ".");
            System.out.println("Better luck next time, " + playerName + "!");
        }
    }

    private int getValidGuess() {
        int guess;
        while (true) {
            System.out.print("Enter your guess (1-100): ");
            if (scanner.hasNextInt()) {
                guess = scanner.nextInt();
                if (guess >= 1 && guess <= 100) {
                    return guess;
                }
            } else {
                scanner.next(); 
            }
            System.out.println("Invalid! Please enter a number between 1 and 100.");
        }
    }

    public void displayStats() {
        System.out.println("========================================");
        System.out.println("GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attemptsUsed);
        System.out.println("Rating: " + getRating());
        System.out.println("========================================");
    }

    private String getRating() {
        if (attemptsUsed == 1) return "Perfect!";
        if (attemptsUsed <= 3) return "Excellent!";
        if (attemptsUsed <= 6) return "Good job!";
        if (attemptsUsed <= 10) return "Nice try!";
        return "Better luck next time!";
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
}