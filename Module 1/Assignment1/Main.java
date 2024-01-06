import java.util.Scanner; //This is used to get input from the console
import java.util.Random; //This is used to generate random numbers
import java.util.Map;
import java.util.HashMap;
//import any additional libraries you need here
import java.util.regex.Pattern;

// Comments on blocks are formatted with a <== ==> and regular line comments are formatted with a regular '//'

class Main {
    public static Scanner input;
    public static Random random;
    // <== For context, the regex format string is this "^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$" ==> //
    // <== I found this regex format string online, so the format string isnt mine. The rest of the format checking is just the basic usage of the regex.Pattern module in java utils. ==> // 
    public static Pattern dateRegex;
    
    public static void main(String[] args) {
        int i; // Used for generic iteration, only so I don't have to use "int i = 0" each time, and instead declare "i = 0" only
        input = new Scanner(System.in);
        random = new Random(); 
        dateRegex = Pattern.compile("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/(\\d{4})$");

        //1) Create a variable and assign it the string "Hello World". Afterwards print that variable to the console.
        System.out.println("\n\nExample 1:");
        String s = "Hello World!";
        System.out.println(s);


        //2) Prompt the user to enter their name, and assign it to a variable. Then print "Hello" followed by their name to the console.
        System.out.println("\n\nExample 2:");
        System.out.println("Enter Your Name:");
        String name = input.nextLine();
        System.out.printf("Hello %s!\n", name);


        //3) Prompt the user to enter an integer, assigning their response to a variable. Then create an if block that will check if that number is less than, equals to, or greater than 0 and prints which case it is back to the user. 
        //Note: that the tests are looking for "equal to", "less than", or "greater than" to be printed.
        System.out.println("\n\nExample 3:");
        int userIn; // Declares the main userInput integer, the value is changed multiple times throughout the code.

        userIn = userInputInt("Input an integer value:", false);

        // <== Checks if the number input is greater than, equal to, or less than 0 and prints its respective degree ==> //
        if (userIn < 0) {
            System.out.println("less than");
        } else if (userIn == 0) {
            System.out.println("equal to");
        } else {
            System.out.println("greater than");
        }

        //4) Prompt the user for a positive number, then use a loop to print all the numbers between 0 and up to but not including that number to the console. Afterwards print out the sum of the numbers printed.
        System.out.println("\n\nExample 4:");

        userIn = userInputInt("Input a positive integer:", true);

        int numSum = 0;

        // <== For Loop to Print Numbers From 0 to userIn-1 and adds each number to the total sum ==> //
        for (i = 0; i < userIn; i++) {
            numSum += i;
            System.out.println(i);
        }

        System.out.println(numSum);


        //5) Using a loop, print out 10 randomly generated numbers between 1 and 100 (inclusive). Afterwards, print out what the smallest, the largest and the average value of the random num ers were.
        //Note: There are no unit tests for this problem due to the pseudorandom nature of the numbers.
        System.out.println("\n\nExample 5:");

        // <== Declares variables for assignment, all with a default value of 0 ==> // 
        int biggest = 0;
        int smallest = 100;
        int sum = 0;

        // <== For loop that generates 10 pseudorandom numbers from 1-100 ==> //
        for (i = 0; i < 10; i++) {
            // <== Random numbers generated by a computer are never truly random, which is why they are called "Pseudorandom" numbers ==> //
            // <== Random numbers are generated with both a seed and a random external source, such as system time ==> //
            int pseudoRand = random.nextInt(100) + 1;

            // <== "if ... else if ... else" block to sort out the biggest and smallest numbers ==> //
            // <== Uses Math.max and Math.min to set the bigger and smaller values accordingly ==> //
            // <== PseudoRandom number is always added to the average ==> //
            biggest = Math.max(biggest, pseudoRand);
            smallest = Math.min(smallest, pseudoRand);
            sum += pseudoRand;

            System.out.println(pseudoRand); // prints the pseudorandom integer.
        }

         float average = sum / i; // Divides the sum of all the psuedorandom numbers by the number of iterations to get the overall average.

        // <== Print Full Results ==> //
        System.out.printf("Biggest Number: %d\n", biggest);
        System.out.printf("Smallest Number: %d\n", smallest);
        System.out.printf("Overall Average: %.2f\n", average);

        //6) Create an input verification loop that won't exit until the user enters a positive integer.
        System.out.println("\n\nExample 6:");

        userIn = userInputInt("Input a positive integer value:", true); // Literally just calls the userInput Function


        //7) Ask the user to enter 2 integers. Create a loop that will count from the first number to the second number and print the results to the console on a single line, seperate by commas. For example, if given the numbers 2 and 5, you should print the string "2,3,4,5". Note that if the first number is larger than the second, you must count backwards!
        System.out.println("\n\nExample 7:");
        int int1 = 0;
        int int2 = 0;

        System.out.println("Enter two integers:");
        // <== Gets two integers as input ==> //
        int1 = userInputInt("Input an integer value:", false);
        int2 = userInputInt("Input an integer value:", false);
        
        if (int1 != int2) {
            int incr = (int) Math.signum(int2 - int1);

            for (i = int1; i != int2+incr; i += incr) {
                System.out.print(i);

                if (i != int2) {
                    System.out.print(",");
                }
            }
        }

        System.out.println();

        //8) Ask the user for their birth day in the form "mm/dd/yyyy" then print out the month, day and year the user was born on seperate lines. Ex: if the user enters "01/23/4567", print "Month: 01", "Day: 23", "Year: 4567".
        System.out.println("\n\nExample 8:");
        String DoB;

        // <== Keeps prompting the user for DoB until the specified format is correct ==> //
        do {
            System.out.println("Enter your date of birth in the form \"mm/dd/yyyy\":");
            DoB = input.nextLine();
        } while (!dateRegex.matcher(DoB).matches());

        String[] DoBArray = DoB.split("/"); // Splits the DoB into a String Array

        System.out.printf("Month: %s\nDay: %s\nYear: %s\n", DoBArray[0], DoBArray[1], DoBArray[2]); // Prints out the month, day and year


        //9) Make a loop that will count from 1 to 30 and print them to the console. However, if the number is a multiple of 3, print "fizz" instead; and if it's a multiple of 5, print "buzz" instead. If it's a multiple of both, print "fizzbuzz" instead of the number.
        System.out.println("\n\nExample 9:");

        // <== Loops 30 times and replaces every number that is a multiple of 3&5 with fizzbuzz, every number that is a multiple of 3 with fizz and every number that is a multiple of 5 with buzz ==> //
        // <== Creates a hashmap with an int key, string value combo, this is used for assigning results based on a key and allows for more dynamic code ==> //
        Map<Integer, String> conditions = new HashMap<Integer, String>();
        conditions.put(3, "fizz");
        conditions.put(5, "buzz");

        // <== The StringBuilder creates a mutable character sequence ==> //
        for (i = 1; i <= 30; i++) {
            StringBuilder result = new StringBuilder();

            // <== A for-each loop looking through all the keys in the conditions hashmap and checking if the remainder of i and the key is 0 ==> //
            // <== Appends fizz, buzz or fizz and buzz to the result depending on the value of i ==> //
            for (int key : conditions.keySet()) {
                if (i % key == 0) {
                    result.append(conditions.get(key));
                }
            }
            
            // <== if the result string doesnt have anything appended to it, it just appends the current value of i ==> //
            if (result.length() == 0) {
                result.append(i);
            }
            System.out.println(result);
        }



        input.close(); //scanners should be closed right before the program ends, if you close it earlier and try to reopen it, you will run into problems as input streams cannot be reopened once closed. 
    }

    public static int userInputInt(String message, boolean positive) {
        int inputInt = -1;

        // <== Checks to make sure the user enters either a number or a positive number depending on the true/false value of the "positive" argument ==> //
        do {
            System.out.println(message);
            if (input.hasNextInt()) {
                inputInt = input.nextInt();

                if (inputInt >= 0 || !positive) {
                    input.nextLine();

                    return inputInt;
                } else {
                    System.out.println("not a positive number");
                }
                
            } else {
                input.nextLine();
                System.out.println("not a number");
                continue;
            }
        } while (true);

    }
}