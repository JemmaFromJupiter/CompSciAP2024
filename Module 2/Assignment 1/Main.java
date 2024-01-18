import java.util.*;

class DoublyLinkedList {
    public Node head;
    public Node tail;
    private int length;

    DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    DoublyLinkedList(int head) {
        this.head = new Node(head);
        this.tail = null;
        this.length = 1;
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
        Node prevNode = getNode(index-1);
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

    public void delete(int index) {
        if (index == 0) {
            head = head.next;
            head.prev = null;

            length -= 1;
            return;
        } else if (index == length-1) {
            tail = tail.prev;
            tail.next = null;

            length -= 1;
            return;
        } else {
            Node prev = getNode(index - 1);
            Node next = getNode(index + 1);

            next.prev = prev;
            prev.next = next;

            length -= 1;
            return;
            
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

public class Main {
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.append(20);
        System.out.println(dll);
        dll.append(21);
        System.out.println(dll);
        dll.insert(19);
        System.out.println(dll);
        dll.append(26);
        System.out.println(dll);
        dll.insert(2, 92);
        System.out.println(dll);
        System.out.println(dll.getLength());
        dll.delete(3);
        System.out.println(dll);
        System.out.println(dll.getLength());
    }
}