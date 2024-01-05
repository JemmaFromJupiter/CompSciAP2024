## Primitive Types

Primitive Types are pre-defined by Java and can be used at any point.

``` java
byte a = 56;                    // Stores whole numbers from -128 to 127. (1-byte)
short b = 14256;                // Stores whole numbers from -32,768 to 32,767. (2-bytes)
int c = 1882345;                // Stores whole numbers from -2,147,483,648 to 2,147,483,647. (4-bytes)
long d = 3784652367;            // Stores whole numbers from -9,223,372,036,854,775,808 to -9,223,372,036,854,775,807. Longs must have an l after the final digit. (8-bytes)
float e = 1.76442;              // Stores fractional numbers. Sufficient for storing 6 to 7 decimal digits. Floats must have an f after the final digit. (4-bytes)
double f = 1.66257355273;       // Stores fractional numbers. Sufficient for storing 15 decimal digits. (8-bytes)
boolean g = true;               // Stores true or false values. (1-bit)
char h = 'A';                   // Stores a single character or ASCII value. (2-bytes) Characters MUST be defined with single-quotes.
```

## Non-Primitive Types

Non-Primitive Types are usually not defined by Java and are most likely User-Defined (With the exception of String).

``` java
String myString = "Hello World!";               // A string in its simplest definition is an Array of Characters. A string contains multiple characters and must be surrounded by double-quotes.
int[] myArray = {1, 2, 3, 4, 5}                 // An array is a way to store multiple values in a single variable. It is defined by putting [] after the primitive type.
```

The third primitive type is Classes. Classes are user-defined. A class is like a blueprint for an object.

An Object is something that is defined based on a class.