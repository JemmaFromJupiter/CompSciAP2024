import java.util.Vector; // Literally ONLY used to balance the binary tree.
import java.util.Random;

public class Main {
  private static Random random = new Random();

  public static int[] makeRandArray(int size, int min, int max) {
    return random.ints(size, min, max).toArray();
  }

  public static void main(String[] args) {
    BinaryTree myBT = new BinaryTree();

    for (int i = 40; i > 0; i--) {
      myBT.insert(i);

      System.out.println(myBT);
    }
  }
}

class BinaryTree {
  // Binary search tree that automatically sorts keys in order.

  Node root;

  // Constructors
  public BinaryTree() {
    this.root = null;
  }

  public BinaryTree(int root) {
    this.root = new Node(root);
  }

  public BinaryTree(int[] data) {
    for (int i : data)
      this.insert(i);
  }

  private class Node {
    // Node class that stores data.

    private int key;
    private Node left, right;

    public Node(int data) {
      this.set(data);
      this.left = null;
      this.right = null;
    }

    public Node getLeft() {
      return this.left;
    }

    public Node getRight() {
      return this.right;
    }

    public int get() {
      return this.key;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public void set(int data) {
      this.key = data;
    }

  }

  // --- Inserts a new node or another binary tree recursively --- //

  public void insert(int key) {
    this.root = this.insert(this.root, new Node(key));
  }

  public void insert(BinaryTree binTree) {
    this.root = this.insert(this.root, binTree.root);
  }

  private Node insert(Node node, Node data) {
    // Sets the node if it is null
    if (node == null) {
      node = data;
      return node;
    }

    // Traverses the left or right subtree accordingly
    if (data.get() < node.get())
      node.setLeft(this.insert(node.getLeft(), data));
    if (data.get() > node.get())
      node.setRight(this.insert(node.getRight(), data));

    return node;
  }

  // ------------------------------------------------------------- //

  // --- Gets the maximum height of the binary tree recursively --- //
  public int getHeight() {
    return this.getHeight(this.root);
  }

  private int getHeight(Node node) {
    if (node == null) {
      return 0;
    } else {
      // Recursively calls getHeight on each node until the node is null
      int lHeight = getHeight(node.getLeft());
      int rHeight = getHeight(node.getRight());

      if (lHeight > rHeight) {
        return (lHeight + 1);
      } else {
        return (rHeight + 1);
      }
    }

  }

  // -------------------------------------------------------------- //

  // --- Searches the binary tree recursively --- //
  public Node search(int key) {
    return this.search(this.root, key);
  }

  private Node search(Node node, int key) {
    if (node == null || node.get() == key)
      return node;

    if (key > node.get())
      return search(node.getRight(), key);

    return search(node.getLeft(), key);

  }

  // -------------------------------------------- //

  // --- Deletes an item from the binary tree recursively --- //
  public void delete(int key) {
    this.delete(this.root, key);
  }

  // This requires a bit of explanation //
  private Node delete(Node node, int key) {
    if (node == null)
      return node;

    // Checks to see if the current nodes data is less than or greater than the key
    // the user is trying to delete.
    if (node.get() > key) {
      // Traverses the left path //

      node.setLeft(delete(node.getLeft(), key));
      return node;

    } else if (node.get() < key) {
      // Traverses the right node

      node.setRight(delete(node.getRight(), key));
      return node;

    }

    // if the node has only one child, the child will replace the node
    if (node.getLeft() == null) {

      Node temp = node.getRight();
      return temp;

    } else if (node.getRight() == null) {

      Node temp = node.getLeft();
      return temp;

    } else {
      // If the node has two children, the node will be replaced by its next in-order
      // successor.
      // successor is always left of its parent

      Node successorParent = node;
      Node successor = node.getRight();

      // finds the successor
      while (successor.getLeft() != null) {

        successorParent = successor;
        successor = successor.getLeft();

      }

      // deletes the successor from the tree
      if (successorParent != node)
        successorParent.setLeft(successor.getRight());
      else
        successorParent.setRight(successor.getRight());

      // Copies the data from the successor to the root
      node.set(successor.get());

      // deletes the successor and returns to the root
      return node;

    }
  }

  // -------------------------------------------------------- //

  // --- Binary Tree Traversal --- //
  public void inOrder() {
    this.inOrder(this.root);
    System.out.println();
  }

  public void preOrder() {
    this.preOrder(this.root);
    System.out.println();
  }

  public void postOrder() {
    this.postOrder(this.root);
    System.out.println();
  }

  private void inOrder(Node node) {
    // Prints the tree in order, so the smallest value to the biggest value.
    if (node != null) {
      this.inOrder(node.left);
      System.out.printf("%d ", node.get());
      this.inOrder(node.right);
    }
  }

  private void preOrder(Node node) {
    // Prints the tree in pre-order, so root comes before everything else.
    if (node != null) {
      System.out.printf("%d ", node.get());
      this.preOrder(node.getLeft());
      this.preOrder(node.getRight());
    }
  }

  private void postOrder(Node node) {
    // Prints tree in post-order, root is the last element printed.
    if (node != null) {
      this.postOrder(node.getLeft());
      this.postOrder(node.getRight());
      System.out.printf("%d ", node.get());
    }
  }

  // ----------------------------- //

  // --- Binary Tree Balancing --- //

  // This is pretty much optional, but I wanted to include it.

  private void storeNodes(Node node, Vector<Node> nodes) {
    // Stores nodes in order in a Vector.
    // this allows the nodes to be separated by indices.
    if (node == null)
      return;

    storeNodes(node.getLeft(), nodes);
    nodes.add(node);
    storeNodes(node.getRight(), nodes);
  }

  private Node balanceTree(Vector<Node> nodes, int start, int end) {
    if (start > end)
      return null;

    // Make the middle element in the Vector the root.
    int mid = (start + end) / 2;
    Node node = nodes.get(mid);

    // construct the right and left trees using in order traversal.
    node.setLeft(balanceTree(nodes, start, mid - 1));
    node.setRight(balanceTree(nodes, mid + 1, end));

    return node;
  }

  public void balanceTree() {
    Vector<Node> nodes = new Vector<Node>();
    this.storeNodes(this.root, nodes);

    int size = nodes.size();
    // Sets the current tree's root to the result of the above function.
    this.root = balanceTree(nodes, 0, size - 1);
  }

  // ----------------------------- //

  // Formats the tree to a nice String.
  // I DO NOT OWN THE PRETTY PRINTING CODE, I got fed up with trying to pretty
  // print it on my own.

  private String fmt(Node root) {

    if (root == null) {
      return "";
    }
    // creates a new string builder used for formatting the pretty string. Appends
    // the root.
    StringBuilder sb = new StringBuilder();
    sb.append(root.get());

    // Declares a Right and Left pointer to show which branch of the tree is being
    // shown
    String pointerRight = "└──";
    String pointerLeft = (root.getRight() != null) ? "├──" : "└──";

    // Builds the left and right sides of the string respectively
    fmt(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
    fmt(sb, "", pointerRight, root.getRight(), false);

    return sb.toString();
  }

  private void fmt(StringBuilder sb, String padding, String pointer, Node node,
      boolean hasRightSibling) {
    // This actually builds the stylised string.
    if (node != null) {
      sb.append("\n");
      sb.append(padding);
      sb.append(pointer);
      sb.append(node.get());

      // This applies padding to the string builder
      StringBuilder paddingBuilder = new StringBuilder(padding);
      // Checks if the node has a right sibling, if so, the node will append a
      // downward "pipe"
      if (hasRightSibling) {
        paddingBuilder.append("│  ");
      } else {
        paddingBuilder.append("   ");
      }

      // Converts the padding to s String and declares new "pointers"
      String paddingForBoth = paddingBuilder.toString();
      String pointerRight = "└──";
      String pointerLeft = (node.getRight() != null) ? "├──" : "└──";

      // Recursively Calls the fmt(sb, padding, pointer, node, hasRightSibling)
      // function.
      fmt(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
      fmt(sb, paddingForBoth, pointerRight, node.getRight(), false);
    }
  }

  public String toString() {
    return fmt(this.root);
  }

}
