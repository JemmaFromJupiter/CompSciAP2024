import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application {

  private int DEFAULT_WIDTH = 1440, DEFAULT_HEIGHT = 720;

  private StorageHandler shdl = new StorageHandler();
  private RuntimeDatabase rdb;

  private MainWindow mainWindow;

  private void getDB() {
    try {
      shdl.setUpConnection();
      shdl.queryDatabase(
          "SELECT s.*, rc.*, ec.* FROM students s LEFT JOIN registered_courses rc ON s.STUDENT_ID = rc.STUDENT_ID LEFT JOIN emergency_contacts ec ON s.STUDENT_ID = ec.STUDENT_ID;");
      this.rdb = shdl.createRuntimeDatabase();
    } catch (Exception e) {
      System.out.println("An Error Occurred.");
      e.printStackTrace();
    }
  }

  public void main() {
    this.getDB();
    this.mainWindow = new MainWindow(this.DEFAULT_WIDTH, this.DEFAULT_HEIGHT, this.rdb);
    this.mainWindow.setVisible(true);
  }
}
