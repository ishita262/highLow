package isc;

import java.util.Scanner;
import java.util.Random;

public class HighLow implements Game {
    private int maxList = 11;
    private int[] numList;
    private int count;
    private int score;
    private final Scanner scanner = new Scanner(System.in);
    private final int HIGHER = 1;
    private final int LOWER = -1;
    private final int INVALID = 0;
    private final int STARTING_POINTS = 100;
    
    
    /** Returns a random integer between 0 and {@code max} inclusive. */
    public int getRandInt(int max) {
        // TODO: generate and return a random number within range
        Random rand = new Random();
        return rand.nextInt(max + 1);
    }

    /**
     * Initialises a random sequence of numbers and resets the game state.
     * The player begins with STARTING_POINTS points.
     */
    public void setUpGame() {
        // TODO: populate numList with random values and initialise count and score
        numList = new int[maxList];
        count = 0;
        score = STARTING_POINTS;

        for (int i = 0; i < maxList; i++) {
            numList[i] = getRandInt(100);
        }

        System.out.println("Starting score: " + score);
    }

    /**
     * Prompts the player to enter their guess and reads it from the console.
     * @return one of the direction constants, or INVALID if the input is not recognised
     */
    public int getUserChoice() {
        // TODO: display prompt, read input, and return the matching constant
        System.out.print("Will the next number be higher or lower? (h/l): ");
        String input = scanner.nextLine().toLowerCase();

        if (input.equals("h")) {
            return HIGHER;
        } 
        else if (input.equals("l")) {
            return LOWER;
        } 
        else {
            System.out.println("Invalid input!");
            return INVALID;
        }
    }

    /**
     * Checks whether the player's guess was correct and updates the score.
     * A correct guess earns points; an incorrect guess loses points; equal numbers are a draw.
     * @param choice the direction constant supplied by the player
     */
    public void checkGuess(int choice) {
        // TODO: evaluate the guess against the sequence and adjust score accordingly
        int current = numList[count];
        int next = numList[count + 1];

        System.out.println("Next number was: " + next);

        if (next == current) {
            System.out.println("Draw!");
            return;
        }

        boolean correct = (choice == HIGHER && next > current) ||(choice == LOWER && next < current);

        if (correct) {
            score += 50;
            System.out.println("Correct! +50 points");
            System.out.println("Your score is now " + score);
        } 
        else {
            score -= 20;
            System.out.println("Wrong! -20 points");
            System.out.println("Your score is now " + score);
        }

        if (score < 0) {
            score = 0;
        }
    }

    /**
     * Plays one turn of the game: shows the current value, collects a guess,
     * and moves to the next position in the sequence.
     * Correct guess: +50 points. Wrong guess: -20 points. Draw: no change.
     */
    public void playTurn() {
        // TODO: display current number, obtain and validate choice, check guess, advance position
        System.out.println("\nCurrent number: " + numList[count]);

        int choice = getUserChoice();

        if (choice != INVALID) {
            checkGuess(choice);
        }

        count++;
    }

    /**
     * Plays a complete round, repeating turns until the sequence ends or the player
     * has no points remaining. Prints an appropriate final message.
     */
    public void playRound() {
        // TODO: loop through turns until a stopping condition is met, then report the outcome
        while (count < maxList - 1 && score > 0) {
            playTurn();
        }

        System.out.println("\nGame Over!");
        System.out.println("Final Score: " + score);
    }
}

