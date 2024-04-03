package module1.assignment2;

import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class Main {
    public static Scanner input;
    public static Random random;

    // <== The main function that runs first on runtime ==>
    public static void main(String[] args) {
        input = new Scanner(System.in);
        // <== Runs the game until "play_game()" returns false ==>
        while (play_game()) {
            ;
        }

        // <== Clears the console and says goodbye to the user ==> //
        clear();
        input.close();
        System.out.println("Goodbye!");
    }

    // <== Clears the console ==> //
    public static void clear() {
        System.out.print("\033\143");
    }

    // <== User Input Verification ==> //
    public static int get_number(String message, int min, int max) {
        int playerNumber = 0;

        // <== Prompts user for number until correct input is selected ==> //
        // <== Also ensures empty inputs and non-numerical inputs are rejected ==> //
        do {
            System.out.println(message);
            if (input.hasNextInt()) {
                playerNumber = input.nextInt();
                if (min < playerNumber && playerNumber < max) {
                    input.nextLine();

                    return playerNumber;
                } else {
                    System.out.printf("Error: Input must be in range %d - %d\n", min, max);
                }
            } else {
                input.nextLine();
                System.out.println("Error: Input needs to be a number.");

                continue;
            }
        } while (true);
    }

    // <== Tells the user if they guessed higher or lower ==> //
    public static String higher_lower(int guess, int number) {
        if (guess > number) {
            return "You Guessed Too High.";
        } else {
            return "You Guessed Too Low.";
        }
    }

    // <== Tells the user if they are hot or cold ==>
    public static String hot_cold(int guess, int number) {
        int diff = Math.abs(guess - number);

        if (diff == 1) {
            return "You're Blazing Hot!";
        } else if (diff <= 3) {
            return "You're Hot!";
        } else if (diff <= 6) {
            return "You're Warm...";
        } else {
            return "You're Cold.";
        }
    }

    // <== This is used when the player decides they dont want either of the above functions as hints ==> //
    public static String no_feedback(int guess, int number) {
        return "Nope, That's Not It.";
    }

    // <== Sets the feedback type to higher/lower, hot/cold or None ==> //
    public static int set_feedback() {
        return get_number("What Kind Of Feedback Would You Like To Help?\n\n1) Higher/Lower\n2) Hot/Cold\n3) None\n", 1, 3);
    }

    // <== Gives the player a hint based on which feedback type they chose ==> //
    public static String feedback(int feedbackType, int guess, int number) {
        switch (feedbackType) {
            case 1:
                return higher_lower(guess, number);
            case 2:
                return hot_cold(guess, number);
            default:
                return no_feedback(guess, number);
        }
    }

    // <== Sets the difficulty of the game by getting a value between 1 and 10, then calculating the upper limit and how many turns the player has based on the difficulty ==> //
    public static int[] set_difficulty() {
        int difficulty = get_number("Choose A Difficulty Level (1-3):", 1, 3);

        int upperLimit = difficulty*10;
        int turns = difficulty+2;
        int[] dif = {upperLimit, turns};

        return dif;
    }

    // <== Plays the game ==> //
    public static boolean play_game() {
        random = new Random(); // Creates a new random number generator object //
        clear();
        System.out.println("Welcome to the guessing game! Try to guess the number I'm thinking of to win!\n\n");

        // <== Sets the difficulty and generates a random number from 1 to the upper limit ==> //
        int[] difficulty = set_difficulty(); int upperLimit = difficulty[0]; int numTurns = difficulty[1];
        int number = random.nextInt(upperLimit) + 1;
        int feedbackType = set_feedback(); // Gets the type of feedback the user wants.
        boolean won = false; // Used to track if the user has won or not
        clear();

        // <== For loop that breaks once the user has won, or when the user uses up all of their tries. ==> //
        for (int turnsRemaining = numTurns; turnsRemaining >= 1; turnsRemaining--) {

            // <== Context and input ==> //
            System.out.printf("I am thinking of a number between 1 and %d.\n", upperLimit);
            System.out.printf("You have %d turns remaining.\n", turnsRemaining);
            int guess = get_number("What do you think it is?", 1, upperLimit);

            // <== If the user guesses the number correctly, the loop breaks and they have won ==> //
            // <== Otherwise, the player gets feedback based on how hot/cold or high/low they are or no feedback ==> //
            if (guess == number) {
                System.out.println("You Got It!");
                won = true;

                break;
            } else {
                clear();
                System.out.println(feedback(feedbackType, guess, number));
            }
        }

        // <== If the player loses, it tells them that they lost ==> //
        if (!won) {
            System.out.printf("You Lost!\nThe Number Was %d.\n", number);
        }

        // <== Prompts the player to play again, returns true or false based on their choice ==> //
        return get_number("Would You Like To Play Again?\n1) Yes\n2) No", 1, 2) == 1;
    }
}

//Mark: EX1 A couple minor issues but overall well done. 