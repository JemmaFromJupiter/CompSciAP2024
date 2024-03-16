
import java.util.Random;
import java.util.Scanner;

public class Main {
  private static Scanner input = new Scanner(System.in);

  public static void main(String[] args) {
    FloodList floodList = new FloodList();
    int boardSize = verifyInput("What is the board size?\nValues from 3 to 20.", 3, 20);
    floodList.makeBoard(boardSize);

    while (!floodList.win()) {
      floodList.displayBoard();
      int userInput = verifyInput("Number: ", 0, 9);
      floodList.move(userInput);
      clearConsole();
    }
    floodList.displayBoard();
    System.out.println("You Won!");
  }

  public static void clearConsole() {
    // Clears the console
    System.out.println("\033[H\033[2J");
  }

  public static int verifyInput(String message, int min, int max) {
    // This function just gets text within a certain range, used for input.
    int userInput = 0;

    while (true) {
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
    }
  }
}

class FloodList {
  // The floodlist class, this holds most of the game functionality.
  private static Random random = new Random();
  private Node head;
  private int size;

  class Node {
    // Node class, this is a linked list Node.
    int data;
    private Node up, down, left, right;
    private boolean visited = false;

    public Node(int data) {
      // gets a "data" argument and sets each other node to null.
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
      // Literally just returns an Array of Nodes...
      return new Node[] { this.up, this.down, this.right, this.left };
    }

    public void applyMove(int move, int initial) {
      // Checks to make sure the node being checked has the same data as the root
      // recursively calls through the NON-NULL neighbors and sets the data to "move"
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
    //
    // Makes 3 different checks, creates new nodes and links them appropriately
    // according
    // to their position in the 2D list.
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
    // Loops through each row and column and prints the value of the node.

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
    // Checks each node to see if it matches the root, if not, returns false.

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
    // Used for testing to make sure the number of rows is correct.
    Node curNode = this.head;
    int i = 0;

    while (curNode != null) {
      i++;
      curNode = curNode.getDown();
    }
    return i;
  }

  public int getCols() {
    // used in testing to make sure the number of columns is correct.
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
