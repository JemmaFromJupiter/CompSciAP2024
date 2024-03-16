package finalProject;

import java.util.ArrayList;
import java.util.Arrays;

public class RuntimeDatabase extends ArrayList<Student> {

  public RuntimeDatabase() {

  }

  public RuntimeDatabase(int size) {
    super(size);
  }

  public RuntimeDatabase(Student[] initialValues) {
    super(Arrays.asList(initialValues));
    this.heapify();
  }

  private int leftChild(int N) {
    return (2 * N) + 1;
  }

  private int rightChild(int N) {
    return (2 * N) + 2;
  }

  public void append(int ID, String FirstName, String LastName, String PreferredFirstName, String PreferredLastName,
      String Gender, String Pronouns, String Email, String DoB) {
    // Do I really need to explain this?
    this.add(
        new Student(ID, FirstName, LastName, PreferredFirstName, PreferredLastName, Gender, Pronouns,
            Email,
            DoB));
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

    if (left < this.size() && this.get(left).getID() < this.get(smallest).getID())
      smallest = left;

    if (right < this.size() && this.get(right).getID() < this.get(smallest).getID())
      smallest = right;

    if (smallest != idx) {
      this.swap(idx, smallest);
      this.heapNode(smallest);
    }
  }

  private void swap(int idx1, int idx2) {
    // Swaps two nodes
    Student temp = this.get(idx1);
    this.set(idx1, this.get(idx2));
    this.set(idx2, temp);
  }

  public Student pop(int idx) {
    // removes the desired value and returns the value
    Student temp = this.get(idx);
    this.remove(this.get(idx));
    this.heapify();
    return temp;
  }

  public Student pop() {
    return pop(0);
  }

  public Student getStudentByID(int ID) {
    return this.get(ID - 1);
  }

  public Object[][] asArray() {
    Object[][] studentArray = new Object[this.size()][6];
    int i = 0;
    for (Student s : this) {
      studentArray[i] = s.asArray();
      i++;
    }
    return studentArray;
  }

}
