import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        
        int targetNumber = random.nextInt((maxRange - minRange) + 1) + minRange;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;
        
        System.out.println("=== Welcome to the Number Guessing Game! ===");
        System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ".");
        System.out.println("You have " + maxAttempts + " attempts to guess it. Good luck!\n");
    
        while (attempts < maxAttempts) {
            System.out.print("Attempt #" + (attempts + 1) + " - Enter your guess: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number. Please try again.\n");
                scanner.next(); 
                continue;
            }
            
            int userGuess = scanner.nextInt();
            attempts++;
            
            if (userGuess < minRange || userGuess > maxRange) {
                System.out.println("Please guess a number within the range (" + minRange + " to " + maxRange + ")!");
            } else if (userGuess == targetNumber) {
                hasGuessedCorrectly = true;
                break; 
            } else if (userGuess < targetNumber) {
                System.out.println("Too low! Try a higher number.");
            } else {
                System.out.println("Too high! Try a lower number.");
            }
            System.out.println(); 
        }
        
        System.out.println("\n=== Game Over ===");
        if (hasGuessedCorrectly) {
            System.out.println("Congratulations! You guessed the number " + targetNumber + " in " + attempts + " attempts!");
        } else {
            System.out.println("Out of attempts! The correct number was: " + targetNumber);
        }
        
        scanner.close();
    }
}