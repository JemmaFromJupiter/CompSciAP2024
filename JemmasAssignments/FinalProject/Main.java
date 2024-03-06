
public class Main {
  public static void main(String[] args) {
    StorageHandler shdl = new StorageHandler();
    RuntimeDatabase rdb = null;
    Application app = new Application();
    app.setUpApp();
    try {
      shdl.setUpConnection();
      shdl.queryDatabase(
          "SELECT s.*, rc.*, ec.* FROM students s INNER JOIN registered_courses rc ON s.STUDENT_ID = rc.STUDENT_ID INNER JOIN emergency_contacts ec ON s.STUDENT_ID = ec.STUDENT_ID;");
      rdb = shdl.createRuntimeDatabase();
    } catch (Exception e) {
      e.printStackTrace();
      return;
    }
  }
}
