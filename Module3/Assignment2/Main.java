import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<Integer>(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });
    System.out.println(queue);
    for (int i : queue) {
      System.out.println(i);
    }
    System.out.println();
    queue.pop();
    for (int i : queue) {
      System.out.println(i);
    }
    System.out.println(queue);
  }
}

class Queue<T> implements Iterable<T> {
  class QueueIterator implements Iterator<T> {
    Node next;

    public QueueIterator(Queue<T> queue) {
      this.next = queue.first;
    }

    public boolean hasNext() {
      return (this.next != null);
    }

    public T next() {
      T temp = this.next.data;
      this.next = this.next.getNext();
      return temp;
    }
  }

  public QueueIterator iterator() {
    return new QueueIterator(this);
  }

  private Node first;
  private Node last;
  private int size;

  public Queue() {
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  public Queue(T data) {
    this.last = new Node(data);
    this.first = this.last;
    this.size = 1;
  }

  public Queue(T[] data) {
    for (T item : data) {
      this.push(item);
    }
  }

  class Node {
    private T data;
    private Node next;

    public Node(T data, Node next) {
      this.data = data;
      this.next = next;
    }

    public Node(T data) {
      this.data = data;
      this.next = null;
    }

    public Node getNext() {
      return this.next;
    }

    public void setNext(Node next) {
      this.next = next;
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

  public boolean isEmpty() {
    return this.first == null;
  }

  public void push(T data) {
    Node newNode = new Node(data);

    if (this.isEmpty()) {
      this.last = newNode;
      this.first = this.last;
      this.size += 1;
      return;
    }

    this.last.setNext(newNode);
    this.last = newNode;
    this.size += 1;

  }

  public T pop() {
    T temp = this.first.data;
    this.first = this.first.getNext();
    return temp;
  }

  public String toString() {
    if (this.isEmpty()) {
      return "Queue is Empty.";
    }
    return String.format("%s, %s", this.last, this.first);
  }
}
