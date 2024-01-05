public class Variables {
    public static void main(String[] args) {
        byte a = 56;                    // Stores whole numbers from -128 to 127. (1-byte)
        short b = 14256;                // Stores whole numbers from -32,768 to 32,767. (2-bytes)
        int c = 1882345;                // Stores whole numbers from -2,147,483,648 to 2,147,483,647. (4-bytes)
        long d = 3784652367l;            // Stores whole numbers from -9,223,372,036,854,775,808 to -9,223,372,036,854,775,807. (8-bytes)
        float e = 1.7644244f;              // Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits. (4-bytes)
        double f = 1.6625735527333333;       // Stores fractional numbers. Sufficient for storing 15 decimal digits. (8-bytes)
        boolean g = true;               // Stores true or false values. (1-bit)
        char h = 'A';                   // Stores a single character or ASCII value. (2-bytes) Characters MUST be defined with single-quotes.

        System.out.printf("Byte: %d\n", a);
        System.out.printf("Short: %d\n", b);
        System.out.printf("Int: %d\n", c);
        System.out.printf("Long: %d\n", d);
        System.out.printf("Float: %.7f\n", e);
        System.out.printf("Double: %.15f\n", f);
        System.out.printf("Boolean: %b\n", g);
        System.out.printf("Char: %c\n", h);
    }
}
