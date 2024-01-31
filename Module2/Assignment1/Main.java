package Module2.Assignment1;

import LinkedLists.DoublyLinkedList;

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