import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class Test {
  File file = null;
  Scanner fileReader;
  Counter charFreqs;
  MinHeap nodeQueue;
  Node root = null;

  public Test(File f) {
    this.charFreqs = new Counter();
    this.file = f;
    this.readFile();
    this.nodeQueue = new MinHeap(this.charFreqs.size());
  }

  public Test(String s) {
    this.charFreqs = new Counter(s);
    this.nodeQueue = new MinHeap(this.charFreqs.size());
  }

  public void compress() {
    for (char c : this.charFreqs.keySet()) {
      nodeQueue.append(new Node(this.charFreqs.get(c), c));
    }

    while (this.nodeQueue.size() > 1) {
      Node x = this.nodeQueue.peek();
      this.nodeQueue.pop();

      Node y = this.nodeQueue.peek();
      this.nodeQueue.pop();

      Node nextRoot = new Node(x.getFrequency() + y.getFrequency(), '-', x, y);
      this.root = nextRoot;

      this.nodeQueue.add(nextRoot);
    }

    this.createBinary(this.root, "", new HashMap<>());

    // if (this.file != null)
    // this.createCompressedFile();
  }

  private void readFile() {
    try {
      this.fileReader = new Scanner(this.file);
      while (this.fileReader.hasNext()) {
        String data = this.fileReader.next();
        this.charFreqs.count(data);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void printData() {
    this.printData(this.root, "");
  }

  private void printData(Node node, String s) {
    if (s == "") {
      System.out.printf("┌--------------------------------------------------------┐%n");
      System.out.printf("| %-11s  | %-21s  | %-13s  |%n", "Character", "Huffman Code", "Byte Value");
      System.out.printf("├--------------┬------------------------┬----------------┤%n");
    }

    if (node.getLeft() == null && node.getRight() == null) {
      System.out.printf("|  %-10s  |  %-20s  |  %-12s  |%n", node.getCharacter(), s, s.getBytes());
      System.out.printf("├--------------┼------------------------┼----------------┤%n");
    }

    if (node.getLeft() != null)
      this.printData(node.getLeft(), s + "0");
    if (node.getRight() != null)
      this.printData(node.getRight(), s + "1");
  }

  private HashMap<Character, byte[]> createBinary(Node node, String s, HashMap<Character, byte[]> hm) {

    if (node.getLeft() == null && node.getRight() == null) {
      hm.put(node.getCharacter(), s.getBytes());
      return hm;
    }

    if (node.getLeft() != null)
      this.createBinary(node.getLeft(), s + "0", hm);

    if (node.getRight() != null)
      this.createBinary(node.getRight(), s + "1", hm);

    return hm;

  }
}
