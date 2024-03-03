
public class RuntimeDatabase {
  private StudentNode root;
  private int StudentCount;

  class StudentNode {
    private Student StudentData;
    private StudentNode Parent;
    private StudentNode Left, Right;

    public StudentNode() {

    }

    public StudentNode(Student StudentData) {
      this.setStudentData(StudentData);
    }

    public Student getStudentData() {
      return this.StudentData;
    }

    public void setStudentData(Student StudentData) {
      this.StudentData = StudentData;
    }

    public StudentNode getParentNode() {
      return this.Parent;
    }

    public void setParent(StudentNode Parent) {
      this.Parent = Parent;
    }

    public StudentNode getRight() {
      return this.Right;
    }

    public StudentNode getLeft() {
      return this.Left;
    }

    public void setRight(StudentNode newRight) {
      this.Right = newRight;
    }

    public void setLeft(StudentNode newLeft) {
      this.Left = newLeft;
    }
  }

  private void addStudent(StudentNode node) {

  }

}
