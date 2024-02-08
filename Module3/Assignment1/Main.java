import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    Stack<String> stack = new Stack<String>(new String[] { "Hello", "World", "!" });
    Stack<String> stack_ = new Stack<String>(new String[] { "This", "is", "a", "Stack" });
    for (String i : stack) {
      System.out.println(i);
    }
    stack.push(stack_);
    for (String i : stack) {
      System.out.println(i);
    }
  }
}

class Stack<T> implements Iterable<T> {
  private class StackIterator implements Iterator<T> {
    Node next;

    public StackIterator(Stack<T> stack) {
      this.next = stack.topLevel;
    }

    public boolean hasNext() {
      return (this.next != null);
    }

    public T next() {
      T temp = this.next.get();
      this.next = this.next.getNext();
      return temp;
    }
  }

  public Iterator<T> iterator() {
    return new StackIterator(this);
  }

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
      if (this.next == null) {
        return this.data.toString();
      }
      return String.format("%s -> %s", this.data.toString(), this.next);
    }

  }

  private Node walkToBottom() {
    Node current = this.topLevel;

    while (current.getNext() != null) {
      current = current.getNext();
    }

    return current;
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

  public void push(Stack<T> stack) {
    Node bottom = stack.walkToBottom();

    bottom.setNext(this.topLevel);
    this.topLevel = stack.topLevel;
    this.size += stack.size();
  }

  public T pop() {
    T tempData = this.topLevel.data;
    this.topLevel = this.topLevel.getNext();
    return tempData;
  }

  public T peek() {
    return this.topLevel.data;
  }

  public boolean isEmpty() {
    return this.topLevel == null;
  }

  public int size() {
    return this.size;
  }

  public boolean has(T val) {
    for (T item : this) {
      if (item == val) {
        return true;
      }
    }
    return false;
  }

  public String toString() {
    if (this.isEmpty()) {
      return "Stack Is Empty.";
    }
    return this.topLevel.toString();
  }

}
