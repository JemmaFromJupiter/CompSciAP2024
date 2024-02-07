
public class Main {
  public static void main(String[] args) {

  }
}

class Stack<T> {
  private Node topLevel;
  private int size;

  Stack() {
    this.topLevel = null;
    this.size = 0;
  }

  Stack(T data) {
    Node newNode = new Node(data);
    this.topLevel = newNode;
    this.size = 1;
  }

  Stack(T[] data) {
  }

  class Node {
    private T data;
    private Node next;

    Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }

    Node(T data) {
      this.data = data;
      this.next = null;
    }

    public void setNext(Node node) {
      this.next = node;
    }

    public Node getNext() {
      return this.next;
    }

    public T getData() {
      return this.data;
    }
  }

  public void push(T data) {
    Node newNode = new Node(data);

    if (this.topLevel == null) {
      this.topLevel = newNode;
      this.size += 1;
      return;
    }

    newNode.setNext(this.topLevel);
    this.topLevel = newNode;
    this.size += 1;
  }

  public Node peek() {
    return this.topLevel;
  }

  public String toString() {
    return "";
  }
}
