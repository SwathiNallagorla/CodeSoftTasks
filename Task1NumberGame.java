import java.util.Scanner;
import java.util.Random;

public class Task1NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attempts = 10;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            System.out.println("\nRound " + (score + 1));
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange);
            System.out.println("You have " + attempts + " attempts to guess it.");

            boolean guessedCorrectly = false;
            while (!guessedCorrectly && attempts > 0) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts--;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number!");
                    guessedCorrectly = true;
                    score++;
                } else if (guess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                if (!guessedCorrectly && attempts > 0) {
                    System.out.println("Attempts left: " + attempts);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you ran out of attempts. The correct number was: " + randomNumber);
            }

            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play another round? (yes/no): ");
            String playAnotherRound = scanner.next();
            if (!playAnotherRound.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
            attempts = 10;
        }

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }
}