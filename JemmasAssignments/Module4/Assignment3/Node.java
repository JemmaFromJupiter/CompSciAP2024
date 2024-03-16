
public class Node {
  private int frequency;
  private char character;
  private Node left, right;

  public Node() {
  }

  public Node(int freq, char chr) {
    this.frequency = freq;
    this.character = chr;
    this.left = null;
    this.right = null;
  }

  public Node(int freq, char chr, Node left, Node right) {
    this.frequency = freq;
    this.character = chr;
    this.left = left;
    this.right = right;
  }

  public int getFrequency() {
    return this.frequency;
  }

  public char getCharacter() {
    return this.character;
  }

  public Node getLeft() {
    return this.left;
  }

  public Node getRight() {
    return this.right;
  }

  public void setFrequency(int freq) {
    this.frequency = freq;
  }

  public void setCharacter(char chr) {
    this.character = chr;
  }

  public void setLeft(Node left) {
    this.left = left;
  }

  public void setRight(Node right) {
    this.right = right;
  }

}
