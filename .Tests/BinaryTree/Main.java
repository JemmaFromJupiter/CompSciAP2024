import java.util.Random;

public class Main {
  private static Random random = new Random();

  public static int[] generateRandomArray(int max, int size) {
    return random.ints(size, 0, max).toArray();
  }

  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree(new int[] { 5, 4, 3, 2, 1, 6, 7, 8 });

    System.out.println(bt);
    System.out.println(bt.search(7));
  }
}

class BinaryTree {
  Node root;

  public BinaryTree() {
    this.root = null;
  }

  public BinaryTree(int root) {
    this.root = new Node(root);
  }

  public BinaryTree(int[] data) {
    this.root = new Node(data[0]);
    for (int i = 1; i < data.length; i++) {
      this.insert(data[i]);
    }
  }

  class Node {
    int data;
    private Node left, right;

    public Node(int data, Node left, Node right) {
      this.data = data;
      this.left = left;
      this.right = right;
    }

    public Node(int data) {
      this.data = data;
      this.left = null;
      this.right = null;
    }

    public void insert(Node node) {
      if (node.data < this.data) {
        if (this.left != null) {
          this.left.insert(node);
        } else {
          this.left = node;
        }
      } else if (node.data > this.data) {
        if (this.right != null) {
          this.right.insert(node);
        } else {
          this.right = node;
        }
      }
    }

    public void insert(BinaryTree bt) {
      this.insert(bt.root);
    }

    public void inOrder() {
      if (this.left != null) {
        this.left.inOrder();
      }
      System.out.printf("%d, ", this.data);
      if (this.right != null) {
        this.right.inOrder();
      }
    }

    public void reverseOrder() {
      if (this.right != null) {
        this.right.reverseOrder();
      }
      System.out.printf("%d, ", this.data);
      if (this.left != null) {
        this.left.reverseOrder();
      }
    }

    public int search(int value) {
      int idx = 0;

      if (value < this.data) {
        idx += this.left.search(value);
      }

      if (value > this.data) {
        idx += this.right.search(value);
      }

      if (value == this.data) {
        return idx + 1;
      }

      if (idx == 0) {
        idx = -1;
      }

      return idx;
    }

    public String toString() {
      return String.format("[%s Left <- %d -> Right %s]", this.left, this.data, this.right);
    }
  }

  public void insert(int data) {

    Node newNode = new Node(data);

    if (this.root == null) {
      this.root = newNode;
      return;
    }

    this.root.insert(newNode);
  }

  public void insert(BinaryTree bt) {
    if (this.root == null) {
      this.root = bt.root;
      return;
    }

    this.root.insert(bt);
  }

  public void inOrder() {
    if (this.root == null) {
      return;
    }
    this.root.inOrder();
    System.out.println();
  }

  public void reverseOrder() {
    if (this.root == null) {
      return;
    }
    this.root.reverseOrder();
    System.out.println();
  }

  public int search(int value) {
    return this.root.search(value);
  }

  public String toString() {
    return root.toString();
  }
}
