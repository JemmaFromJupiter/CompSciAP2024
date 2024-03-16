package module2.assignment1;

import java.util.Random;

public class Main {
  private static Random random = new Random();

  public static int[] generateRandomArray(int max, int size) {
    return random.ints(size, 0, max).toArray();
  }

  public static void main(String[] args) {
    int[] randomIntArray = generateRandomArray(100, 20);
    DoublyLinkedList dll = new DoublyLinkedList(randomIntArray);
    System.out.println(dll);
    System.out.println(dll.get(10));
    dll.swap(2, 14);
    System.out.println(dll);
    dll.swap(1, 19);
    System.out.println(dll);
    System.out.println(dll.split(5, 15));
    dll.append(new DoublyLinkedList(new int[] { 1, 2, 3, 4, 5, 6 }));
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

  public DoublyLinkedList split(int startIdx, int endIdx) {
    DoublyLinkedList newDLL = new DoublyLinkedList();
    for (int i = startIdx; i <= endIdx; i++) {
      newDLL.append(this.get(i));
    }
    return newDLL;
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

    Node tempNode = node1;

    node1 = node2;
    node2 = tempNode;

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
