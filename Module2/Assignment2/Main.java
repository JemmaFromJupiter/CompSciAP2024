import java.util.Random;

public class Main {
  private static Random random = new Random();

  public static void main(String[] args) {
    int[] randArr = new int[10];
    for (int i = 0; i < 10; i++) {
      randArr[i] = random.nextInt(100);
    }
    DoublyLinkedList dll = new DoublyLinkedList(randArr);
    System.out.println(dll);
    dll.bubbleSort();
    System.out.println(dll);
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

  public void swap(int idx1, int idx2) {
    Node node1 = this.getNode(idx1);
    Node node2 = this.getNode(idx2);
    Node tempPrev = node1.prev;
    Node tempNext = node1.next;

    node1.prev = node2.prev;
    node1.next = node2.next;

    node2.prev = tempPrev;
    node2.next = tempNext;

    if (node2.prev != null) {
      node2.prev.next = node2;
    }

    if (node2.next != null) {
      node2.next.prev = node2;
    }

    if (node1.prev != null) {
      node1.prev.next = node1;
    }

    if (node1.next != null) {
      node1.next.prev = node1;
    }
  }

  public void bubbleSort() {
    //
    boolean swapped;
    for (int i = 0; i < this.length; i++) {
      swapped = false;
      for (int j = 0; j < this.length - i - 1; j++) {
        if (this.get(j) < this.get(j + 1)) {
          swapped = true;
          this.swap(j, j + 1);

        }
      }
      if (swapped == false) {
        return;
      }
    }
  }

  public String toString() {
    StringBuilder returnString = new StringBuilder();
    Node last = head;
    while (last != null) {
      if (last.prev != null) {
        returnString.append("<- ");
      } else {
        returnString.append("Head :: ");
      }
      returnString.append(last.data);
      if (last.next != null) {
        returnString.append(" ->");
      } else {
        returnString.append(" :: Tail");
      }
      last = last.next;
    }
    return returnString.toString();
  }

  public int getLength() {
    return length;
  }
}
