import java.util.Random;

public class Main {
  private static Random random = new Random();

  public static int[] generateRandomArray(int max, int size) {
    return random.ints(size, 0, max).toArray();
  }

  public static void main(String[] args) {
    int[] randArr = generateRandomArray(100, 10);

    DoublyLinkedList dll = new DoublyLinkedList(randArr);
    DoublyLinkedList dll2 = new DoublyLinkedList(randArr);

    System.out.println(dll);
    dll.bubbleSort();
    System.out.println(dll);

    System.out.println(dll2);
    dll2.insertionSort();
    System.out.println(dll2);
  }
}

class DoublyLinkedList {
  private Node head;
  private Node tail;
  private int length;

  DoublyLinkedList() {
    this.head = null;
    this.tail = null;
    this.length = 0;
  }

  DoublyLinkedList(int head) {
    Node newNode = new Node(head);
    this.head = newNode;
    this.tail = newNode;
    this.length = 1;
  }

  DoublyLinkedList(int[] a) {
    this();
    for (int n : a) {
      this.append(n);
    }
  }

  class Node {
    int data;
    Node prev;
    Node next;

    Node(int data, Node prev, Node next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    Node(int data) {
      this.data = data;
      this.prev = null;
      this.next = null;
    }

    public String toString() {
      if (this.prev != null && this.next != null) {
        return String.format("Node: [Prev: %d, This: %d, Next: %d]", this.prev.data, this.data, this.next.data);
      } else if (this.prev == null) {
        return String.format("Node: [Prev: null, This: %d, Next: %d]", this.data, this.next.data);
      } else if (this.next == null) {
        return String.format("Node: [Prev: %d, This: %d, Next: null]", this.prev.data, this.data);
      } else {
        return String.format("Node: [Prev: null, This: %d, Next: null]", this.data);
      }
    }
  }

  public Node getNode(int index) {
    Node currentNode = head;
    for (int currentIndex = 0; currentIndex < index; currentIndex++) {
      currentNode = currentNode.next;
    }

    return currentNode;
  }

  public int get(int idx) {
    return this.getNode(idx).data;
  }

  public void set(int idx, int data) {
    Node node = this.getNode(idx);
    node.data = data;
  }

  public void insert(int newData) {
    Node newNode = new Node(newData);

    if (head == null) {
      head = newNode;
      tail = newNode;
      length += 1;
      return;
    }

    newNode.next = head;
    head.prev = newNode;

    head = newNode;
    length += 1;
  }

  public void insert(int index, int newData) {

    Node newNode = new Node(newData);
    Node prevNode = this.getNode(index - 1);
    Node nextNode = prevNode.next;

    if (head == null) {
      head = newNode;
      tail = newNode;
      length += 1;
      return;
    }

    prevNode.next = newNode;
    newNode.next = nextNode;
    newNode.prev = prevNode;
    nextNode.prev = newNode;

    length += 1;
  }

  public void append(int newData) {
    Node newNode = new Node(newData);

    if (head == null) {
      head = newNode;
      tail = newNode;
      length += 1;
      return;
    }

    tail.next = newNode;
    newNode.prev = tail;
    tail = newNode;

    length += 1;

  }

  public void append(DoublyLinkedList l) {
    Node last = l.head;
    last.prev = this.tail;
    this.tail.next = last;
    this.tail = l.tail;

    length += l.getLength();

    return;
  }

  public void delete(int index) {
    if (index == 0) {
      head = head.next;
      head.prev = null;

      length -= 1;
      return;
    }

    if (index == length - 1) {
      tail = tail.prev;
      tail.next = null;

      length -= 1;
      return;
    }

    Node node = this.getNode(index);

    node.prev.next = node.next;
    node.next.prev = node.prev;

    length -= 1;
    return;

  }

  public int pop(int idx) {
    int nodeValue = this.getNode(idx).data;
    this.delete(idx);
    return nodeValue;
  }

  public int pop() {
    return this.pop(length - 1);
  }

  public int find(int val) {
    int idx;

    for (idx = 0; idx < length; idx++) {
      if (this.getNode(idx).data == val) {
        return idx;
      }
    }
    return -1;
  }

  private int getIndex(Node n) {
    int idx = 0;
    while (n != this.head) {
      n = n.prev;
      idx++;
    }
    return idx;
  }

  private boolean adjacent(Node obj1, Node obj2) {
    return (obj1.next == obj2 || obj2.prev == obj1);
  }

  public void swap(int idx1, int idx2) {
    // System.out.printf("%d, %d\n", idx1, idx2);
    //
    // Checks to make sure idx1 and idx2 don't get mixed up.
    if (idx1 > idx2) {
      int tIdx = idx1;
      idx1 = idx2;
      idx2 = tIdx;
    }

    Node node1 = this.getNode(idx1);
    Node node2 = this.getNode(idx2);

    Node tempPrev = node1.prev;
    Node tempNext;

    if (adjacent(node1, node2)) {
      tempNext = node1;
      node1.prev = node2;
    } else {
      tempNext = node1.next;
      node1.prev = node2.prev;
    }
    node1.next = node2.next;

    node2.prev = tempPrev;
    node2.next = tempNext;

    if (node2.prev != null) {
      node2.prev.next = node2;
    } else {
      this.head = node2;
    }

    if (node2.next != null) {
      node2.next.prev = node2;
    } else {
      this.tail = node2;
    }

    if (node1.prev != null) {
      node1.prev.next = node1;
    } else {
      this.head = node1;
    }

    if (node1.next != null) {
      node1.next.prev = node1;
    } else {
      this.tail = node1;
    }

  }

  public void swap(Node obj1, Node obj2) {
    int idx1 = this.getIndex(obj1);
    int idx2 = this.getIndex(obj2);
    // System.out.printf("idx1: %d, idx2: %d\n", idx1, idx2);
    swap(idx1, idx2);
  }

  public void bubbleSort() {
    //
    boolean swapped;

    for (int i = 0; i < this.length; i++) {
      swapped = false;
      for (int j = 0; j < this.length - i - 1; j++) {
        Node cur = this.getNode(j);
        if (cur.data > cur.next.data) {
          this.swap(cur, cur.next);
          swapped = true;
        }
      }
      if (swapped == false) {
        return;
      }
    }
  }

  public void insertionSort() {
    int i = 1;

    while (i < this.length) {
      int j = i;

      Node cur = this.getNode(j);

      while (j > 0 && cur.prev.data > cur.data) {
        this.swap(cur, cur.prev);
        j = j - 1;
      }

      i = i + 1;
    }
  }

  public String toString() {
    StringBuilder returnString = new StringBuilder();
    System.out.println("Working...");
    Node last = head;
    returnString.append("[");
    while (last != null) {
      // System.out.println(last);
      if (last.next != null)
        returnString.append(String.format("%d,", last.data));
      else
        returnString.append(String.format("%d", last.data));
      last = last.next;
    }
    returnString.append("]");
    return returnString.toString();
  }

  public int getLength() {
    return length;
  }
}
