import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class Main {
    public static void main(String[] args) {

    }

    public static void clear() {
    System.out.print("\033\143");
    }

    public static int get_number(String message, int min, int max) {
        Scanner input = new Scanner(System.in);
        int playerNumber = 0;
        
        do {
            input.nextLine();
            try {
                playerNumber = input.nextInt();
            } catch (IllegalArgumentException e) {
                System.out.println("Error: Input needs to be a number.");
                continue;
            }
            
            if (playerNumber < min) {
                System.out.printf("Error: Input must be at least %i\n", min);
            } else if (playerNumber > max) {
                System.out.printf("Error: Input must be at most %i\n", max);
            } else {
                return playerNumber;
            }
        } while (true);

    }

    public static String higher_lower(int guess, int number) {
        if (guess > number) {
            return "You Guessed Too High.";
        }
        return "You Guessed Too High.";
    }

    public static String hot_cold(int guess, int number) {
        int diff = Math.abs(guess - number);
        
        if (diff == 1) {
            return "You're Blazing Hot!";
        } else if (diff <= 3) {
            return "You're Hot!";
        } else if (diff <= 6) {
            return "You're Warm...";
        }
        return "You're Cold.";
    }

    public static String no_feedback(int guess, int number) {
        return "Nope, That's Not It.";
    }

    public static int set_feedback() {
        return get_number("What Kind Of Feedback Would You Like To Help?\n\n1) Higher/Lower\n2) Hot/Cold\n None\n\n", 1, 3);
    }
}