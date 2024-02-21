import java.util.ArrayList;
import java.util.Arrays;

public class MinHeap extends ArrayList<Node> {

  public MinHeap(int size) {
    super(size);
  }

  public MinHeap(Node[] initialValues) {
    super(Arrays.asList(initialValues));
    this.heapify();
  }

  public Node peek() {
    return this.get(0);
  }

  private int leftChild(int N) {
    return (2 * N) + 1;
  }

  private int rightChild(int N) {
    return (2 * N) + 2;
  }

  public void append(Node key) {
    // Do I really need to explain this?
    this.add(key);
    this.heapify();
  }

  private void heapify() {
    // Literally just a for loop going backwards.
    for (int i = this.size() - 1; i >= 0; i--) {
      this.heapNode(i);
    }
  }

  private void heapNode(int idx) {
    // Checks the left child and right child and checks to see if they are smaller
    // than the smallest
    // if so, replace the smallest.
    int left = this.leftChild(idx);
    int right = this.rightChild(idx);

    int smallest = idx;

    if (left < this.size() && this.get(left).getFrequency() < this.get(smallest).getFrequency())
      smallest = left;

    if (right < this.size() && this.get(right).getFrequency() < this.get(smallest).getFrequency())
      smallest = right;

    if (smallest != idx) {
      this.swap(idx, smallest);
      this.heapNode(smallest);
    }
  }

  private void swap(int idx1, int idx2) {
    // Swaps two nodes
    Node temp = this.get(idx1);
    this.set(idx1, this.get(idx2));
    this.set(idx2, temp);
  }

  public Node pop(int idx) {
    // removes the desired value and returns the value
    Node temp = this.get(idx);
    this.remove(this.get(idx));
    this.heapify();
    return temp;
  }

  public Node pop() {
    return pop(0);
  }

  public boolean isMinHeap(int idx) {
    // Checks each side of the heap to make sure each root is the smallest among its
    // children
    int N = this.size();
    int leftChild = this.leftChild(idx);

    if (leftChild < N) {
      if (this.get(leftChild).getFrequency() < this.get(idx).getFrequency())
        return false;
      if (!isMinHeap(leftChild))
        return false;
    }

    int rightChild = this.rightChild(idx);
    if (rightChild < N) {
      if (this.get(rightChild).getFrequency() < this.get(idx).getFrequency())
        return false;
      if (!isMinHeap(rightChild))
        return false;
    }
    return true;
  }

}
