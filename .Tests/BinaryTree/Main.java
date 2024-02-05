
public class Main {
  public static void main(String[] args) {
    BinaryTree bt = new BinaryTree();
    bt.insert(10);
    bt.insert(5);
    bt.insert(15);
    bt.insert(20);
    bt.insert(0);

    System.out.println(bt);
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

  class Node {
    int data;
    private Node left;
    private Node right;

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

    public String toString() {
      return String.format("[%s Right <- %d -> Left %s]", this.left, this.data, this.right);
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

  public String toString() {
    return root.toString();
  }
}
