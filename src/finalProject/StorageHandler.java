package finalProject;

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
  private String password = "";

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
  
  public void addStudentToDatabase(Student s) throws SQLException {
	  String sql = "INSERT INTO students (STUDENT_ID, LEGAL_NAME, PREFERRED_NAME, GENDER, PRONOUNS, EMAIL, DOB)" +
	  "VALUES (?, ?, ?, ?, ?, ?, ?)";
	  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, s.getID());
		  preparedStatement.setString(2, s.getLegalName());
		  preparedStatement.setString(3, s.getPreferredName());
		  preparedStatement.setString(4, s.getGender());
		  preparedStatement.setString(5, s.getPronouns());
		  preparedStatement.setString(6, s.getEmail());
		  preparedStatement.setString(7, s.getDoB());
		  preparedStatement.execute();
	  }
  }
  
  public void addRegisteredCourseToDatabase(String ID, Student.RegisteredCourse rc) throws SQLException {
	  String sql = "INSERT INTO registered_courses (STUDENT_ID, COURSE_ID, COURSE_NAME, COURSE_CREDITS, COURSE_GRADE)" +
	  "VALUES (?, ?, ?, ?, ?)";
	  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, ID);
		  preparedStatement.setString(2, rc.getCourseID());
		  preparedStatement.setString(3, rc.getCourseName());
		  preparedStatement.setInt(4, rc.getCourseCredits());
		  preparedStatement.setDouble(5, 0.0);
		  preparedStatement.execute();
	  }
  }
  
  public void addEmergencyContactToDatabase(String ID, Student.EmergencyContact ec) throws SQLException {
	  String sql = "INSERT INTO emergency_contacts (STUDENT_ID, CONTACT_ID, CONTACT_NAME, CONTACT_HOME, CONTACT_CELL, CONTACT_EMAIL)" +
	  "VALUES (?, ?, ?, ?, ?, ?)";
	  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, ID);
		  preparedStatement.setString(2, ec.getContactID());
		  preparedStatement.setString(3, ec.getContactName());
		  preparedStatement.setString(4, ec.getContactHome());
		  preparedStatement.setString(5, ec.getContactCell());
		  preparedStatement.setString(6, ec.getContactEmail());
		  preparedStatement.execute();
	  }
  }
  
  public void updateStudentInfo(Student s) throws SQLException {
	  String sql = "UPDATE students " +
			  "SET LEGAL_NAME=?, PREFERRED_NAME=?, EMAIL=?, GENDER=?, PRONOUNS=? " +
			  "WHERE STUDENT_ID=?;";
	  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, s.getLegalName());
		  preparedStatement.setString(2, s.getPreferredName());
		  preparedStatement.setString(3, s.getEmail());
		  preparedStatement.setString(4, s.getGender());
		  preparedStatement.setString(5, s.getPronouns());
		  preparedStatement.setString(6, s.getID());
		  preparedStatement.executeUpdate();
	  }
  }
  
  public void updateRegisteredCourseInfo(String studentID, Student.RegisteredCourse rc) throws SQLException {
	  String sql = "UPDATE registered_courses " +
			  "SET COURSE_GRADE=?" +
			  "WHERE STUDENT_ID=? AND COURSE_ID=?;";
	  
	  try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
		  preparedStatement.setDouble(1, rc.getTotalGrade());
		  preparedStatement.setString(2, studentID);
		  preparedStatement.setString(3, rc.getCourseID());
		  preparedStatement.executeUpdate();
	  }
  }
  
  public void updateEmergencyContactInfo(String studentID, Student.EmergencyContact ec) throws SQLException {
	  
  }
  
  public void deleteFromDatabase(String ID)throws SQLException {
	  this.deleteStudentFromRegisteredCourses(ID);
	  this.deleteFromEmergencyContacts(ID);
	  this.deleteFromStudents(ID);
  }
  
  public void deleteStudentFromRegisteredCourses(String studentID) throws SQLException {
	  String sql = "DELETE FROM registered_courses WHERE STUDENT_ID=?;";
	  try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, studentID);
		  preparedStatement.executeUpdate();
	  }
  }
  
  public void deleteCourseFromRegisteredCourses(String courseID) throws SQLException {
	  String sql = "DELETE FROM registered_courses WHERE COURSE_ID=?;";
	  try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, courseID);
		  preparedStatement.executeUpdate();
	  }
  }
  
  public void deleteFromEmergencyContacts(String studentID) throws SQLException {
	  String sql = "DELETE FROM emergency_contacts WHERE STUDENT_ID=?;";
	  try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, studentID);
		  preparedStatement.executeUpdate();
	  }
  }
  
  public void deleteContactFromEmergencyContacts(String contactID) throws SQLException {
	  String sql = "DELETE FROM emergency_contacts WHERE CONTACT_ID=?;";
	  try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, contactID);
		  preparedStatement.executeUpdate();
	  }
  }
  
  private void deleteFromStudents(String studentID) throws SQLException {
	  String sql = "DELETE FROM students WHERE STUDENT_ID=?;";
	  try (PreparedStatement preparedStatement = this.connection.prepareStatement(sql)) {
		  preparedStatement.setString(1, studentID);
		  preparedStatement.executeUpdate();
	  }
  }

  public RuntimeDatabase createRuntimeDatabase() throws SQLException {
    RuntimeDatabase newdb = new RuntimeDatabase();
    String currentStudentID = "-1";
    while (this.resultSet.next()) {
      // Gets all the necessary information from the database
      String StudentID = this.resultSet.getString("STUDENT_ID");
      String LegalName = this.resultSet.getString("LEGAL_NAME");
      String PrefName = this.resultSet.getString("PREFERRED_NAME");
      String Gender = this.resultSet.getString("GENDER");
      String Pronouns = this.resultSet.getString("PRONOUNS");
      String Email = this.resultSet.getString("EMAIL");
      String DoB = this.resultSet.getString("DoB");

      String CourseID = this.resultSet.getString("COURSE_ID");
      String CourseName = this.resultSet.getString("COURSE_NAME");
      Integer CourseCredits = this.resultSet.getInt("COURSE_CREDITS");
      Double CourseGrade = this.resultSet.getDouble("COURSE_GRADE");

      String ContactID = this.resultSet.getString("CONTACT_ID");
      String ContactName = this.resultSet.getString("CONTACT_NAME");
      String ContactHome = this.resultSet.getString("CONTACT_HOME");
      String ContactCell = this.resultSet.getString("CONTACT_CELL");
      String ContactEmail = this.resultSet.getString("CONTACT_EMAIL");

      // Appends the information to the runtime database.
      if (!StudentID.equals(currentStudentID)) {
    	System.out.println("Adding Student.");
        newdb.append(StudentID, LegalName, PrefName, Gender, Pronouns, Email, DoB);
        currentStudentID = StudentID;
      }
      System.out.println("Student ID being called: " + StudentID);
      System.out.println("Student ID currently in use: " + currentStudentID);
      
      if (CourseID != null && !newdb.getStudentByID(currentStudentID).courseRegistered(CourseID))
    	  newdb.getStudentByID(currentStudentID).addRegisteredCourse(CourseID, CourseName, CourseCredits, CourseGrade);

      if (ContactID != null && !newdb.getStudentByID(currentStudentID).emergencyContactRegistered(ContactID))
    	  newdb.getStudentByID(currentStudentID).addEmergencyContact(ContactID, ContactName, ContactHome, ContactCell, ContactEmail);
      
    }
    return newdb;
  }

  public ResultSet getResultSet() {
    return this.resultSet;
  }
}
