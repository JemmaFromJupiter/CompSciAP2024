public class VariableAssignment {
    public static void main(String[] args) {
        int x = 10;

        x += 5; // Add to x. Equivalent to x = x + 5
        System.out.println(x);
        x -= 5; // Subtract from x. Equivalent to x = x - 5
        System.out.println(x);
        x *= 5; // Multiplying x. Equivalent to x = x * 5;
        System.out.println(x);
        x /= 5; // Dividing x. Equivalent to x = x / 5
        System.out.println(x);
        x %= 5; // Remainder of x. Equivalent to x = x % 5
        System.out.println(x);
    }
}
