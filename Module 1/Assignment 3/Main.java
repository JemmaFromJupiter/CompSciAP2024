import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static Scanner input;
    private static List<String> yes = Arrays.asList("Yes", "yes", "Y", "y");
    private static List<String> no = Arrays.asList("No", "no", "N", "n");
    public static void main(String[] args) {
        input = new Scanner(System.in);
        
        int userInt = get_number("Input an integer between 1 and 3: ", 1, 3);
        float userFloat = get_number("Input a float between 1.0 and 3.0", 1.0f, 3.0f);
        boolean userBool = confirm("Confirm?");

        System.out.printf("The Input Integer: %d\n", userInt);
        System.out.printf("The Input Float: %f\n", userFloat);
        System.out.printf("The Input Confirmation: %b\n", userBool);
    }

    public static int get_number(String message, int min, int max) {
        int userNum = 0;

        do {
            System.out.println(message);
            if (input.hasNextInt()) {
                userNum = input.nextInt();
                if (min <= userNum && userNum <= max) {
                    input.nextLine();

                    return userNum;
                } else {
                    System.out.printf("Input must be within the range %d - %d.\n", min, max);
                }
            } else {
                input.nextLine();
                System.out.println("Input Must Be An Integer.");

                continue;
            }
        } while (true);
    }

    public static float get_number(String message, float min, float max) {
        float userNum = 0.0f;

        do {
            System.out.println(message);
            if (input.hasNextFloat()) {
                userNum = input.nextFloat();
                if (min <= userNum && userNum <= max) {
                    input.nextLine();

                    return userNum;
                } else {
                    System.out.printf("Input must be within the range %f - %f.\n", min, max);
                }
            } else {
                input.nextLine();
                System.out.println("Input Must Be A Float.");

                continue;
            }
        } while (true);
    }

    public static boolean confirm(String message) {
        String userInput = "";

        do {
            System.out.println(message);
            if (input.hasNext()) {
                userInput = input.nextLine();
                if (yes.contains(userInput)) {
                    return true;
                } else if (no.contains(userInput)) {
                    return false;
                } else {
                    System.out.println("Input must be Y or N.");
                }
            } else {
                input.nextLine();

                System.out.println("Input must not be empty.");
            }
        } while (true);
    }
}
