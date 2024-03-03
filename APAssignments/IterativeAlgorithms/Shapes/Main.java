
public class Main {
  public static void main(String[] args) {
    printSquare(5);
    System.out.println();
    printHollowSquare(5);
    System.out.println();
    printTriangle(5);
    System.out.println();
    printHollowTriangle(5);
  }

  public static void printHollowSquare(int size) {
    for (int i = 0; i < size; i++) {
      if (i == 0 || i == size - 1) {
        System.out.print("* ".repeat(size));
      } else {
        System.out.print("*" + " ".repeat(2 * size - 3) + "*");
      }
      System.out.println();
    }

  }

  public static void printHollowTriangle(int size) {
    for (int i = 1; i <= size; i++) {
      System.out.print(" ".repeat(size - i));
      if (i == 1 || i == size) {
        System.out.print("* ".repeat(i));
      } else {
        System.out.print("*" + " ".repeat(2 * i - 3) + "*");
      }
      System.out.println();
    }
  }

  public static void printSquare(int size) {
    for (int i = 0; i < size; i++) {
      System.out.println("* ".repeat(size));
    }
  }

  public static void printTriangle(int size) {
    for (int i = 0; i <= size; i++) {
      System.out.println(" ".repeat(size - i) + "* ".repeat(i));
    }
  }
}
