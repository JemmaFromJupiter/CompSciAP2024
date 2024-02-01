import java.util.Random;
import Module2.LinkedList.DoublyLinkedList;

public class Main {
    private static Random random = new Random();
    public static void main(String[] args) {
        int[] randomIntArray = new int[20];
        for (int i = 0; i < randomIntArray.length; i++) {
            randomIntArray[i] = random.nextInt(100);
        }
        DoublyLinkedList dll = new DoublyLinkedList(randomIntArray);
        System.out.println(dll);
        System.out.println(dll.get(19));
        dll.swap(2, 14);
        System.out.println(dll);
    }
}