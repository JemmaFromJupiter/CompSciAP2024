import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class Compressor {
  private File file = null;
  private Counter charFreqs;
  private MinHeap nodeQueue;
  private String fileContents;
  private Map<Character, String> encodingMap;
  private Node root = null;

  public Compressor(File f) {
    this.charFreqs = new Counter();
    this.file = f;
    this.fileContents = this.readFile();
    charFreqs.count(this.fileContents);
    this.nodeQueue = new MinHeap(this.charFreqs.size());
  }

  public Compressor(String s) {
    this.charFreqs = new Counter(s);
    this.nodeQueue = new MinHeap(this.charFreqs.size());
  }

  private void buildTree() {
    // Builds a huffman tree based off of the frequency of each character.

    // Appends each character and its frequency to a new Node in the nodeQueue.
    for (char c : this.charFreqs.keySet()) {
      nodeQueue.append(new Node(this.charFreqs.get(c), c));
    }

    while (this.nodeQueue.size() > 1) {
      // Pops two values from the node queue and adds them together in a new node.
      // Makes the new node the root, and appends the new node to the nodeQueue.
      Node x = this.nodeQueue.peek();
      this.nodeQueue.pop();

      Node y = this.nodeQueue.peek();
      this.nodeQueue.pop();

      Node nextRoot = new Node(x.getFrequency() + y.getFrequency(), '-', x, y);
      this.root = nextRoot;

      this.nodeQueue.add(nextRoot);
    }
  }

  private String readFile() {
    // Reads the entire input file and returns a string with the file contents.
    StringBuilder content = new StringBuilder();
    try (Scanner fileReader = new Scanner(this.file)) {
      fileReader.useDelimiter("\\Z");
      while (fileReader.hasNext()) {
        content.append(fileReader.next());
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return content.toString();
  }

  public void printData() {
    // Prints out all the data in a table. Not gonna explain each block, should be
    // self explanitory.
    int idx = 0;

    // Top of Table
    System.out.printf("┌─────────────────────────────────────────────────────┐%n");
    System.out.printf("│ %s %-34d │%n", "CharSet Length: ", this.charFreqs.size());
    System.out.printf("├────────┬───────────┬────────────────────────────────┤%n");
    System.out.printf("│ %-6s │ %-9s │ %-30s │%n", "Freqs", "Character", "Byte Value");
    System.out.printf("├────────┼───────────┼────────────────────────────────┤%n");

    // Different Rows
    for (char c : this.encodingMap.keySet()) {
      if (c == '\n')
        System.out.printf("│ %-6s │ %-9s │ %-30s │%n", this.charFreqs.get(c), "\\n", this.encodingMap.get(c));
      else
        System.out.printf("│ %-6s │ %-9s │ %-30s │%n", this.charFreqs.get(c), c, this.encodingMap.get(c));

      if (idx < this.encodingMap.size() - 1)
        System.out.printf("├────────┼───────────┼────────────────────────────────┤%n");
      else
        System.out.printf("└────────┴───────────┴────────────────────────────────┘%n");
      idx++;
    }
  }

  private void encode() {
    // Populates the encodingMap.
    this.encodingMap = new HashMap<>();
    this.generateEncodingMap(this.root, "");
  }

  private void generateEncodingMap(Node node, String code) {
    // populates the encodingMap with each characters encoded value.
    if (node == null) {
      return;
    }

    // puts a value in the map if it is a leaf node.
    if (node.getLeft() == null && node.getRight() == null) {
      this.encodingMap.put(node.getCharacter(), code);
    }

    // recursive calls
    this.generateEncodingMap(node.getLeft(), code + "0");
    this.generateEncodingMap(node.getRight(), code + "1");

  }

  private String encodeString(String s) throws IllegalStateException {
    // encodes an individual string and returns the encoded string

    // throws an error if the encodingMap is not populated
    if (this.encodingMap == null)
      throw new IllegalStateException(
          "Encoding Map Has Not Been Generated. Call Compressor.encode() To Generate Encoding Map.");

    // uses a stringbuilder to encode the string and return a new string.
    StringBuilder encodedString = new StringBuilder();
    for (char c : s.toCharArray())
      encodedString.append(this.encodingMap.get(c));

    return encodedString.toString();
  }

  private byte[] convertToByte(String encodedData) {

    // converts input encoded data to a byteArray that is used to write to a
    // BufferedOutputStream in other functions.

    // gets the number of bytes in the encoded data (8 bits to 1 byte).
    // and makes a new byte array of that size.
    int numBytes = (int) Math.ceil(encodedData.length() / 8);
    byte[] bytes = new byte[numBytes];

    // a for loop appending each byte to bytes.
    for (int i = 0; i < numBytes; i++) {
      int start = i * 8;
      int end = Math.min(start + 8, encodedData.length());
      String byteString = encodedData.substring(start, end);

      // appends a byte with a base two byte value.
      bytes[i] = (byte) Integer.parseInt(byteString, 2);
    }

    return bytes;
  }

  private void writeEncodedDataToFile(String outputFile, String encodedString) {
    // uses a BufferedOutputStream to write the compressed bytes to a file as actual
    // bytes.

    try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputFile))) {
      byte[] bytes = convertToByte(encodedString); // Function from above. useful eh?
      outputStream.write(bytes);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }

  public void compress() {
    // compresses the data and writes the compressed bytes to a file.
    // deletes original file afterwards.
    this.buildTree();
    this.encode();

    String fileName = this.file.getName().substring(0, this.file.getName().lastIndexOf(".")) + ".bin";
    this.writeEncodedDataToFile(fileName, this.encodeString(this.fileContents));
    this.file.delete();
  }
}
