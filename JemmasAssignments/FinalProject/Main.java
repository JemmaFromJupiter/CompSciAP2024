
public class Main {
  public static void main(String[] args) {
    StorageHandler shdl = new StorageHandler();
    RuntimeDatabase rdb = null;
    try {
      shdl.setUpConnection();
      shdl.updateDatabase(
          "UPDATE students SET PREF_FIRST_NAME=NULL WHERE STUDENT_ID=1");
    } catch (Exception e) {
      e.printStackTrace();
    }
    // System.out.println(rdb);
  }
}
