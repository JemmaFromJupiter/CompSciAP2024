import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class Main {
    public static void main(String[] args) {

        while (play_game()) {
            ;
        }

        clear();
        System.out.println("Goodbye!");
    }

    public static void clear() {
    System.out.print("\033\143");
    }

    public static int get_number(String message, int min, int max) {
        Scanner input = new Scanner(System.in);
        int playerNumber = 0;
        
        do {
            System.out.println(message);
            if (input.hasNextInt()) {
                playerNumber = input.nextInt();
                if (playerNumber < min) {
                    System.out.printf("Error: Input must be at least %d\n", min);
                } else if (playerNumber > max) {
                    System.out.printf("Error: Input must be at most %d\n", max);
                } else {
                    return playerNumber;
                }
            } else {
                input.nextLine();
                System.out.println("Error: Input needs to be a number.");
                continue;
            }
        } while (true);
    }

    public static String higher_lower(int guess, int number) {
        if (guess > number) {
            return "You Guessed Too High.";
        } else {
            return "You Guessed Too Low.";
        }
    }

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

    public static String no_feedback(int guess, int number) {
        return "Nope, That's Not It.";
    }

    public static int set_feedback() {
        int feedback = get_number("What Kind Of Feedback Would You Like To Help?\n\n1) Higher/Lower\n2) Hot/Cold\n3) None\n", 1, 3);
        
        return feedback;
    }

    public static int[] set_difficulty() {
        int difficulty = get_number("Choose A Difficulty Level (1-3):", 1, 3);
        int upperLimit = difficulty*10;
        int turns = difficulty+2;
        int[] dif = {upperLimit, turns};
        return dif;
    }

    public static boolean play_game() {
        Random random = new Random();
        clear();
        System.out.println("Welcome to the guessing game! Try to guess the number I'm thinking of to win!\n\n");
        int[] difficulty = set_difficulty(); int upperLimit = difficulty[0]; int numTurns = difficulty[1];
        int number = random.nextInt(upperLimit) + 1;
        int feedback = set_feedback();
        boolean won = false;
        clear();

        for (int turnsRemaining = numTurns; turnsRemaining >= 1; turnsRemaining--) {
            // System.out.println(turnsRemaining);
            System.out.printf("I am thinking of a number between 1 and %d.\n", upperLimit);
            System.out.printf("You have %d turns remaining.\n", turnsRemaining);
            int guess = get_number("What do you think it is?", 1, upperLimit);

            if (guess == number) {
                System.out.println("You Got It!");
                won = true;
                break;
            } else {
                clear();
                switch (feedback) {
                    case 1:
                        System.out.println(higher_lower(guess, number));
                        break;
                    case 2:
                        System.out.println(hot_cold(guess, number));
                        break;
                    default:
                        System.out.println(no_feedback(guess, number));
                        break;
                }
            }
        }

        if (!won) {
            System.out.printf("You Lost!\nThe Number Was %d.\n", number);
        }

        return get_number("Would You Like To Play Again?\n1) Yes\n2) No", 1, 2) == 1;
    }
}