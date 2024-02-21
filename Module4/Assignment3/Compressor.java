import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

public class Compressor {
  File file = null;
  Scanner fileReader;
  Counter charFreqs;
  MinHeap nodeQueue;
  Node root = null;

  public Compressor(File f) {
    this.charFreqs = new Counter();
    this.file = f;
    this.readFile();
    this.nodeQueue = new MinHeap(this.charFreqs.size());
  }

  public Compressor(String s) {
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
    int idx = 0;
    HashMap<Character, byte[]> bytes = this.createBinary(this.root, "", new HashMap<>());

    // Top of Table
    System.out.printf("┌──────────────────────────────────────┐%n");
    System.out.printf("│ %s %-19d │%n", "CharSet Length: ", this.charFreqs.size());
    System.out.printf("├────────┬───────────┬─────────────────┤%n");
    System.out.printf("│ %-6s │ %-9s │ %-15s │%n", "Freqs", "Character", "Byte Value");
    System.out.printf("├────────┼───────────┼─────────────────┤%n");

    // Different Rows
    for (char c : bytes.keySet()) {
      System.out.printf("│ %-6s │ %-9s │ %-15s │%n", this.charFreqs.get(c), c, bytes.get(c));
      if (idx < bytes.size() - 1) {
        System.out.printf("├────────┼───────────┼─────────────────┤%n");
      } else {
        System.out.printf("└────────┴───────────┴─────────────────┘%n");
      }
      idx++;
    }
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
