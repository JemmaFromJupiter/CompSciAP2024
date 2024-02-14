import java.util.ArrayList;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    MinHeap mHeap = new MinHeap(20);

    for (int i = 20; i > 0; i--) {
      mHeap.append(i);
    }

    System.out.println(mHeap);
    System.out.println(mHeap.isMinHeap(0));

    mHeap.pop();

    System.out.println(mHeap);
    System.out.println(mHeap.isMinHeap(0));

  }

}

class MinHeap extends ArrayList<Integer> {

  public MinHeap(int size) {
    super(size);
  }

  public MinHeap(Integer[] initialValues) {
    super(Arrays.asList(initialValues));
    this.heapify();
  }

  private int parent(int N) {
    return (int) (N - 1) / 2;
  }

  private int leftChild(int N) {
    return (2 * N) + 1;
  }

  private int rightChild(int N) {
    return (2 * N) + 2;
  }

  public void append(int key) {
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

    if (left < this.size() && this.get(left) < this.get(smallest))
      smallest = left;

    if (right < this.size() && this.get(right) < this.get(smallest))
      smallest = right;

    if (smallest != idx) {
      this.swap(idx, smallest);
      this.heapNode(smallest);
    }
  }

  private void swap(int idx1, int idx2) {
    // Swaps two nodes
    int temp = this.get(idx1);
    this.set(idx1, this.get(idx2));
    this.set(idx2, temp);
  }

  public int pop(int idx) {
    // removes the desired value and returns the value
    int temp = this.get(idx);
    this.remove(this.get(idx));
    this.heapify();
    return temp;
  }

  public int pop() {
    return pop(0);
  }

  public boolean isMinHeap(int idx) {
    // Checks each side of the heap to make sure each root is the smallest among its
    // children
    int N = this.size();
    int leftChild = this.leftChild(idx);

    if (leftChild < N) {
      if (this.get(leftChild) < this.get(idx))
        return false;
      if (!isMinHeap(leftChild))
        return false;
    }

    int rightChild = this.rightChild(idx);
    if (rightChild < N) {
      if (this.get(rightChild) < this.get(idx))
        return false;
      if (!isMinHeap(rightChild))
        return false;
    }
    return true;
  }

}
