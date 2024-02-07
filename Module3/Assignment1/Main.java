
public class Main {
  public static void main(String[] args) {
    Stack<Runnable> stack = new Stack<Runnable>();
    stack.push(new Runnable() {
      public void run() {
        System.out.println("Hello World!");
      }
    });
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
    this.topLevel = new Node(data);
    this.size = 1;
  }

  Stack(T[] data) {
    for (T item : data) {
      this.push(item);
    }
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

    public T get() {
      return this.data;
    }

    public void set(T data) {
      this.data = data;
    }

    public String toString() {
      return this.data.toString();
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

  public T pop() {
    T tempData = this.topLevel.data;
    this.topLevel = this.topLevel.next;
    return tempData;
  }

  public Node peek() {
    return this.topLevel;
  }

  public boolean isEmpty() {
    return this.topLevel == null;
  }

  public int size() {
    return this.size;
  }

  public String toString() {
    return "";
  }

}

class FunctionStack<Runnable> implements Stack<Runnable> {

}
