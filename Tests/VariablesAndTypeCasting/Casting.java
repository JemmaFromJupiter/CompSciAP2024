public class Casting {
    public static void main(String[] args) {
        // <== Automatic Casting ==> //
        int myInt = 9;
        double myDouble = myInt; // Automatic cast: int to double.

        System.out.println(myInt);
        System.out.println(myDouble);

        // <== Manual Casting to Type ==> //
        double secondDouble = 1.967;
        int secondInt = (int) secondDouble; // Manual cast: double to int.

        System.out.println(secondDouble);
        System.out.println(secondInt);
    }
}
