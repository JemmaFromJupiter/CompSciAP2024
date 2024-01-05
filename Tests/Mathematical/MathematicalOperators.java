public class MathematicalOperators {
    public static void main(String[] args) {
        int x = 50;
        int y = 10;
        int add = x + y;
        int sub = x - y;
        int mul = x * y;
        float div = x / y;
        int mod = x % y;
        int incr = ++x;
        int deincr = --y;

        System.out.printf("Addition: %d\nSubtraction: %d\nMultiplication: %d\nDivision: %f\nRemainder: %d\nIncrement: %d\nDeincrement: %d\n", add, sub, mul, div, mod, x, y);
    }
}
