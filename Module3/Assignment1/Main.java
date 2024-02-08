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
  // A Stack using the First in Last out Data Structure
  private class StackIterator implements Iterator<T> {
    // Creates an iterable class for easy forloop traversal
    Node next;

    public StackIterator(Stack<T> stack) {
      this.next = stack.topLevel;
    }

    public boolean hasNext() {
      // checks if the next node is null
      return (this.next != null);
    }

    public T next() {
      // Gets the next node in the stack
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

  // Constructors, Self explanitory.
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
    // A node class, stores data and structures the stack
    private T data;
    private Node next;

    // Constructors
    Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }

    Node(T data) {
      this.data = data;
      this.next = null;
    }

    // Setters and Getters
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
      // Formats a nice string for the stack
      if (this.next == null) {
        return this.data.toString();
      }
      return String.format("%s -> %s", this.data.toString(), this.next);
    }

  }

  private Node walkToBottom() {
    // Walks to the first node in the stack.
    Node current = this.topLevel;

    while (current.getNext() != null) {
      current = current.getNext();
    }

    return current;
  }

  public void push(T data) {
    // Pushes a new piece of data to the top of the stack
    Node newNode = new Node(data);

    if (this.isEmpty()) {
      this.topLevel = newNode;
      this.size += 1;
      return;
    }

    newNode.setNext(this.topLevel);
    this.topLevel = newNode;
    this.size += 1;
  }

  public void push(Stack<T> stack) {
    // Pushes another stack to the top of the current stack
    Node bottom = stack.walkToBottom();

    bottom.setNext(this.topLevel);
    this.topLevel = stack.topLevel;
    this.size += stack.size();
  }

  public T pop() {
    // removes the top of the stack and returns the data stored in it.
    T tempData = this.topLevel.data;
    this.topLevel = this.topLevel.getNext();
    return tempData;
  }

  public T peek() {
    // shows the topLevel
    return this.topLevel.get();
  }

  public boolean isEmpty() {
    // checks if the stack is empty
    return this.topLevel == null;
  }

  public int size() {
    // returns the size of the stack
    return this.size;
  }

  public boolean has(T val) {
    // Checks to see if a value is in the stack
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
