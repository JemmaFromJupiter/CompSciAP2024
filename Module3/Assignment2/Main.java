import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<Integer>(new Integer[] { 1, 2, 3, 4, 5 });
    Queue<Integer> queue_ = new Queue<Integer>(new Integer[] { 6, 7, 8, 9, 10 });
    queue.push(queue_);
    queue.push(10);
    queue.push(12);
    System.out.println(queue);
    System.out.println(queue.has(5));
    for (int i : queue) {
      System.out.println(queue);
      queue.pop();
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
    this.first = new Node(data);
    this.last = this.first;
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
      this.first = newNode;
      this.last = this.first;
      this.size += 1;
      return;
    }

    this.last.setNext(newNode);
    this.last = newNode;
    this.size += 1;

  }

  public void push(Queue<T> queue) {
    this.last.setNext(queue.first);
    this.last = queue.last;
    this.size += queue.size();
  }

  public T pop() {
    T temp = this.first.get();
    this.first = this.first.getNext();
    return temp;
  }

  public boolean has(T val) {
    for (T item : this) {
      if (item == val) {
        return true;
      }
    }
    return false;
  }

  public int size() {
    return this.size;
  }

  public String toString() {
    if (this.isEmpty()) {
      return "Queue is Empty.";
    }
    return this.first.toString();
  }
}
