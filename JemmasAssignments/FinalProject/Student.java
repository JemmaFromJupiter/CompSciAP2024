import java.util.regex.Pattern;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Student {
  private Pattern dobPattern = Pattern.compile("^(0[1-9]|1[1-9]|[12][0-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})$");

  private int ID;
  private String FirstName;
  private String LastName;
  private String PreferredFirstName;
  private String PreferredLastName;
  private String Email;
  private String Gender;
  private String Pronouns;
  private String DoB;
  private ArrayList<RegisteredCourse> RegisteredCourses;
  // private ArrayList<String> EmergencyContacts;

  public Student(int ID, String FirstName, String LastName, String PreferredFirstName, String PreferredLastName,
      String Gender, String Pronouns, String Email, String DoB) {
    this.setID(ID);
    this.setFirstName(FirstName);
    this.setLastName(LastName);
    this.setPreferredFirstName(PreferredFirstName);
    this.setPreferredLastName(PreferredLastName);
    this.setGender(Gender);
    this.setPronouns(Pronouns);
    this.setEmail(Email);
    this.setDoB(DoB);
    this.RegisteredCourses = new ArrayList<>();
  }

  class RegisteredCourse {
    private String CourseID;
    private String CourseName;
    private Double TotalGrade;

    public RegisteredCourse(String CourseID, String CourseName) {
      this.CourseID = CourseID;
      this.CourseName = CourseName;
      this.TotalGrade = 0.0;
    }

    public RegisteredCourse(String CourseID, String CourseName, Double CourseGrade) {
      this.CourseID = CourseID;
      this.CourseName = CourseName;
      this.TotalGrade = CourseGrade;
    }

    public String getCourseID() {
      return this.CourseID;
    }

    public void setCourseID(String newCourseID) {
      this.CourseID = newCourseID;
    }

    // Course Name Setters and Getters
    public String getCourseName() {
      return this.CourseName;
    }

    public void setCourseName(String newName) {
      this.CourseName = newName;
    }

    // Total Grade Utils
    public Double getTotalGrade() {
      return this.TotalGrade;
    }

    public void setTotalGrade(Double newGrade) {
      this.TotalGrade = newGrade;
    }

    public String toString() {
      return String.format("%n\t\t[%s, %s, %.2f]", this.CourseID, this.CourseName, this.TotalGrade);
    }

  }

  // ID Getter and Setter
  public int getID() {
    return this.ID;
  }

  private void setID(int newID) {
    this.ID = newID;
  }

  // Name Getters and Setters
  public String getFirstName() {
    return this.FirstName;
  }

  public String getLastName() {
    return this.LastName;
  }

  public String getPreferredFirstName() {
    return this.PreferredFirstName;
  }

  public String getPreferredLastName() {
    return this.PreferredLastName;
  }

  public void setFirstName(String newFirstName) {
    this.FirstName = newFirstName;
  }

  public void setLastName(String newLastName) {
    this.LastName = newLastName;
  }

  public void setPreferredFirstName(String newPreferredFirstName) {
    this.PreferredFirstName = newPreferredFirstName;
  }

  public void setPreferredLastName(String newLastName) {
    this.PreferredLastName = newLastName;
  }

  public String getEmail() {
    return this.Email;
  }

  public void setEmail(String newEmail) {
    this.Email = newEmail;
  }

  // Gender
  public String getGender() {
    return this.Gender;
  }

  public String getPronouns() {
    return this.Pronouns;
  }

  public void setGender(String newGender) {
    this.Gender = newGender;
  }

  public void setPronouns(String newPronouns) {
    this.Pronouns = newPronouns;
  }

  // Date of Birth Setters and Getters
  public String getDoB() {
    return this.DoB;
  }

  public void setDoB(String newDoB) {
    if (this.dobPattern.matcher(newDoB).matches() && this.DoB == null) {
      this.DoB = newDoB;
    } else {
      throw new InvalidParameterException("Date of Birth Text Must Match dd/mm/yyyy.");
    }
  }

  // Courses

  public ArrayList<RegisteredCourse> getRegisteredCourses() {
    return this.RegisteredCourses;
  }

  public void addRegisteredCourse(String CourseID, String CourseName) {
    this.RegisteredCourses.add(new RegisteredCourse(CourseID, CourseName));
  }

  public void addRegisteredCourse(String CourseID, String CourseName, Double CourseGrade) {
    this.RegisteredCourses.add(new RegisteredCourse(CourseID, CourseName, CourseGrade));
  }

  public void removeRegisteredCourse(String CourseID) {
    for (RegisteredCourse rc : this.RegisteredCourses) {
      if (rc.getCourseName() == CourseID) {
        this.RegisteredCourses.remove(rc);
      }
    }
  }

  public String toString() {
    return String.format("%n\t[%d, %s, %s, %s, %s, %s, %s, %s, %s, %s]", this.getID(), this.getFirstName(),
        this.getLastName(),
        this.getPreferredFirstName(), this.getPreferredLastName(),
        this.getGender(), this.getPronouns(), this.getEmail(), this.getDoB(), this.getRegisteredCourses());
  }
}
