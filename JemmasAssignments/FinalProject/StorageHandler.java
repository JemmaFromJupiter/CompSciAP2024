import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StorageHandler {
  private Connection connection = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  private String jdbcUrl = "jdbc:mysql://127.0.0.1/student_database";

  private String user = "root";
  private String password = "SystemPassword123!";

  public void setUpConnection() throws Exception {
    Class.forName("com.mysql.cj.jdbc.Driver");

    this.connection = DriverManager
        .getConnection(jdbcUrl, user, password);
  }

  public void queryDatabase(String RuntimeQuery) throws SQLException {
    // Executes a Query that reads the database.
    this.preparedStatement = connection.prepareStatement(RuntimeQuery);
    this.resultSet = this.preparedStatement.executeQuery();
  }

  public void updateDatabase(String RuntimeUpdate) throws SQLException {
    // creates an SQL Update and sends it to the server
    this.preparedStatement = this.connection.prepareStatement(RuntimeUpdate);
    this.preparedStatement.executeUpdate();
  }

  public RuntimeDatabase createRuntimeDatabase() throws SQLException {
    RuntimeDatabase newdb = new RuntimeDatabase();
    int currentStudentID = -1;
    while (this.resultSet.next()) {
      // System.out.println(newdb);
      // Gets all the necessary information from the database
      int StudentID = this.resultSet.getInt("STUDENT_ID");
      String FirstName = this.resultSet.getString("FIRST_NAME");
      String LastName = this.resultSet.getString("LAST_NAME");
      String PrefFirst = this.resultSet.getString("PREF_FIRST_NAME");
      String PrefLast = this.resultSet.getString("PREF_LAST_NAME");
      String Gender = this.resultSet.getString("GENDER");
      String Pronouns = this.resultSet.getString("PRONOUNS");
      String Email = this.resultSet.getString("EMAIL");
      String DoB = this.resultSet.getString("DoB");

      String CourseID = this.resultSet.getString("COURSE_ID");
      String CourseName = this.resultSet.getString("COURSE_NAME");
      Double CourseGrade = this.resultSet.getDouble("COURSE_GRADE");

      String ContactID = this.resultSet.getString("CONTACT_ID");
      String ContactName = this.resultSet.getString("CONTACT_NAME");
      String ContactHome = this.resultSet.getString("CONTACT_HOME");
      String ContactCell = this.resultSet.getString("CONTACT_CELL");
      String ContactEmail = this.resultSet.getString("CONTACT_EMAIL");

      // Appends the information to the runtime database.
      if (StudentID != currentStudentID) {
        newdb.append(StudentID, FirstName, LastName, PrefFirst, PrefLast, Gender, Pronouns, Email, DoB);
        currentStudentID = StudentID;
      }

      if (CourseID != null && !newdb.getStudentByID(StudentID).courseRegistered(CourseID))
        newdb.getStudentByID(StudentID).addRegisteredCourse(CourseID, CourseName, CourseGrade);

      if (ContactID != null && !newdb.getStudentByID(StudentID).emergencyContactRegistered(ContactID))
        newdb.getStudentByID(StudentID).addEmergencyContact(ContactID, ContactName, ContactHome, ContactCell,
            ContactEmail);
    }
    return newdb;
  }

  public ResultSet getResultSet() {
    return this.resultSet;
  }
}
