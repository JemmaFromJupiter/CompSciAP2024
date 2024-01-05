public class Output {
    public static void main(String[] args) {
        // <== Prints line by line ==> //
        System.out.println("Hello World!");

        // <== Prints a formatted string, does not insert newline at the end of output ==> //
        // %c       : Character or Char
        // %s       : String
        // %d       : Decimal (Integer) Value (Base-10)
        // %e       : Exponential Floating-Point Number
        // %f       : Floating-Point Number
        //      %.xf        : Specifies how many decimal places are displayed. x is replaced by any POSITIVE integer.
        // %x       : Number In Hexadecimal (Base-16)
        System.out.printf("Character Format Specifier %%c: %c\n", 'F');
        System.out.printf("String Format Specifier %%s: %s\n", "GenericName");
        System.out.printf("Decimal Format Specifier %%d: %d\n", 16);
        System.out.printf("Exponential Floating-Point Format Specifier %%e: %e\n", 1.7e15);
        System.out.printf("Floating-Point Format Specifier %%f: %f\n", 1.6673544278735);
        System.out.printf("Floating-Point To 2 Places Format Specifier %%.2f: %.2f\n", 1.6673544278735);
        System.out.printf("Hexadecimal Format Specifier %%x: %x\n", 0x23D);

        // <== Prints Normally on a line, Does not insert newline at the end of output ==> //
        System.out.print("Hello!\n");
        System.out.print("Hello!\n");
    }
}
