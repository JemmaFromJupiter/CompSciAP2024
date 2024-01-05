import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class myClass {
    int x;
}

class Main {
    public static void main(String[] args) {
    // <== Global variables, Used all throughout the program ==> //
        Scanner input = new Scanner(System.in);
        Random random = new Random();
        myClass newMyClass = new myClass();

        newMyClass.x = 17;

        System.out.println(newMyClass.x);
    }

    public static void clear() {
    System.out.print("\033\143");
    }
}