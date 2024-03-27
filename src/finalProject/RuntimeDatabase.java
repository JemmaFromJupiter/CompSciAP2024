package finalProject;

import java.util.ArrayList;
import java.util.Arrays;

public class RuntimeDatabase extends ArrayList<Student> {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1;

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

  public void append(String ID, String LegalName, String PreferredName,
      String Gender, String Pronouns, String Email, String DoB) {
    // Do I really need to explain this?
    this.add(
        new Student(ID, LegalName, PreferredName, Gender, Pronouns,
            Email,
            DoB));
    this.heapify();
  }
  
  public void append(Student student) {
	    // Do I really need to explain this?
	    this.add(student);
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

    if (left < this.size() && Integer.parseInt(this.get(left).getID()) < Integer.parseInt(this.get(smallest).getID()))
      smallest = left;

    if (right < this.size() && Integer.parseInt(this.get(right).getID()) < Integer.parseInt(this.get(smallest).getID()))
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

  public Student getStudentByID(String ID) {
	for (Student s : this) {
		if (s.getID() == ID) {
			return s;
		}
	}
	return null;
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
