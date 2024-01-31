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
        dll.delete(3);
        System.out.println(dll);
    }
}

class DoublyLinkedList {
    public Node head;
    public Node tail;
    private int length;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.length = 0;
    }

    public DoublyLinkedList(int head) {
        Node newNode = new Node(head);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public DoublyLinkedList(int[] a) {
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
        Node prevNode = this.getNode(index-1);
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
            Node node = this.getNode(index);

            node.prev.next = node.next;
            node.next.prev = node.prev;

            length -= 1;
            return;
            
        }
    }

    public int pop(int idx) {
        int nodeValue = this.getNode(idx).data;
        this.delete(idx);
        return  nodeValue;
    }

    public int pop() {
        return this.pop(length-1);
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
        int dat1 = this.get(idx1);
        int dat2 = this.get(idx2);

        this.set(idx1, dat2);
        this.set(idx2, dat1);
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