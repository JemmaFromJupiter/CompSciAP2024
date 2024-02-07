import java.util.Random;
import java.util.Scanner;

public class Main {
  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    FloodList fl = new FloodList();
    int boardSize = verifyInput("What is the board size?\nValues from 3 to 20.", 3, 20);
    fl.makeBoard(boardSize);

    while (!fl.win()) {
      fl.displayBoard();
      int userInput = verifyInput("Number: ", 0, 9);
      fl.move(userInput);
      System.out.println();
    }
    fl.displayBoard();
    System.out.println("You Won!");
  }

  public static void clearConsole() {
    System.out.println("\033[2J");
  }

  public static int verifyInput(String message, int min, int max) {
    int userInput = 0;

    do {
      System.out.println(message);
      if (input.hasNextInt()) {
        userInput = input.nextInt();
        if (min <= userInput && userInput <= max) {
          input.nextLine();
          return userInput;
        } else {
          input.nextLine();
          System.out.printf("\033[31mInput must be between %d and %d\033[0m\n", min, max);
        }
      } else {
        input.nextLine();
        System.out.println("\033[31mInput must be a number.\033[0m");
      }
    } while (true);
  }
}

class FloodList {
  private static Random random = new Random();
  private Node head;
  private int size;

  class Node {
    int data;
    private Node up, down, left, right;
    private boolean visited = false;

    public Node(int data) {
      this.data = data;
      this.up = null;
      this.down = null;
      this.left = null;
      this.right = null;
    }

    public Node getUp() {
      return this.up;
    }

    public Node getRight() {
      return this.right;
    }

    public Node getDown() {
      return this.down;
    }

    public Node getLeft() {
      return this.left;
    }

    public boolean getVisited() {
      return this.visited;
    }

    public void setUp(Node node) {
      this.up = node;
    }

    public void setRight(Node node) {
      this.right = node;
    }

    public void setDown(Node node) {
      this.down = node;
    }

    public void setLeft(Node node) {
      this.left = node;
    }

    public void reset() {
      this.visited = false;
    }

    private Node[] getNeighbors() {
      return new Node[] { this.up, this.down, this.right, this.left };
    }

    public void applyMove(int move, int initial) {
      if (this.data != initial || this.visited == true) {
        return;
      }

      this.visited = true;

      // System.out.println("\nNode Neighbors:");
      for (Node neighbor : this.getNeighbors()) {
        if (neighbor != null) {
          neighbor.applyMove(move, initial);
        }
      }
      this.data = move;
      this.reset();
    }
  }

  public void move(int move) {
    this.head.applyMove(move, this.head.data);
  }

  public void makeBoard(int size) {
    // This function hurt me for a while.
    Node rowStart = this.head;
    Node curCol = rowStart;
    this.size = size;

    for (int row = 0; row <= size; row++) {
      for (int col = 0; col < size; col++) {

        int val = random.nextInt(9) + 1;
        Node newNode = new Node(val);

        if (row == 0 && col == 0) {
          this.head = newNode;
          rowStart = this.head;
          curCol = rowStart;
        } else if (row == 0) {
          newNode.setLeft(curCol);
          curCol.setRight(newNode);
          curCol = curCol.getRight();
        } else if (col == 0) {
          newNode.setUp(rowStart);
          rowStart.setDown(newNode);
          rowStart = newNode;
          curCol = newNode;
        } else {
          newNode.setLeft(curCol);
          newNode.setUp(curCol.up.right);
          newNode.getUp().setDown(newNode);
          curCol.setRight(newNode);
          curCol = curCol.getRight();
        }
      }
    }
  }

  public void displayBoard() {
    Node rowStart = this.head;
    Node curNode = rowStart;

    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        System.out.printf("%d ", curNode.data);
        curNode = curNode.getRight();
      }
      System.out.println();
      rowStart = rowStart.getDown();
      curNode = rowStart;
    }
  }

  public boolean win() {
    Node rowStart = this.head;
    Node curNode = rowStart;

    for (int row = 0; row < this.size; row++) {
      for (int col = 0; col < this.size; col++) {
        if (curNode.data != this.head.data) {
          return false;
        }
        curNode = curNode.getRight();
      }
      rowStart = rowStart.getDown();
      curNode = rowStart;
    }
    return true;
  }

  public int getRows() {
    Node curNode = this.head;
    int i = 0;

    while (curNode != null) {
      i++;
      curNode = curNode.getDown();
    }
    return i;
  }

  public int getCols() {
    Node curNode = this.head;
    int i = 0;

    while (curNode != null) {
      i++;
      curNode = curNode.getRight();
    }
    return i;
  }

  public Node getHead() {
    return this.head;
  }

  public int getSize() {
    return this.size;
  }
}
