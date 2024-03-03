import java.util.regex.Pattern;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student {
  private Pattern dobPattern = Pattern.compile("^(0[1-9]|[12][0-9]|3[0-1])/(0[1-9]|[1][0-2])/(\\d{4})$");

  private int ID;
  private String LegalName;
  private String PreferredName;
  private String Gender;
  private String Pronouns;
  private String DoB;
  private ArrayList<RegisteredCourse> RegisteredCourses;

  public Student(int ID, String LegalName, String PreferredName, String Gender, String Pronouns, String DoB) {
    this.setID(ID);
    this.setLegalName(LegalName);
    this.setPreferredName(PreferredName);
    this.setGender(Gender);
    this.setPronouns(Pronouns);
    this.setDoB(DoB);
  }

  class RegisteredCourse {
    private String CourseName;
    private Map<String, Double> CourseOutcomes;
    private Double TotalGrade;

    public RegisteredCourse(String CourseName) {
      this.CourseName = CourseName;
      this.CourseOutcomes = new HashMap<>();
      this.TotalGrade = 0.0;
    }

    public RegisteredCourse(String CourseName, String[] CourseOutcomes) {
      this.CourseName = CourseName;
      this.CourseOutcomes = new HashMap<>();
      for (String s : CourseOutcomes) {
        this.addOutcome(s);
      }
      this.TotalGrade = 0.0;
    }

    // Course Name Setters and Getters
    public String getCourseName() {
      return this.CourseName;
    }

    public void setCourseName(String newName) {
      this.CourseName = newName;
    }

    // Course Outcomes Functions
    public Map<String, Double> getOutcomes() {
      return this.CourseOutcomes;
    }

    public Double getOutcomeGrade(String OutcomeName) {
      return this.CourseOutcomes.get(OutcomeName);
    }

    public void addOutcome(String OutcomeName) {
      this.CourseOutcomes.put(OutcomeName, 0.0);
    }

    public void updateOutcomeGrade(String OutcomeName, Double Grade) {
      this.CourseOutcomes.replace(OutcomeName, Grade);
      this.calculateGradeAverage();
    }

    // Total Grade Utils
    public Double getTotalGrade() {
      return this.TotalGrade;
    }

    public void setTotalGrade(Double newGrade) {
      this.calculateGradeAverage();
    }

    private void calculateGradeAverage() {
      Double sum = 0.0;
      int size = this.CourseOutcomes.size();
      for (Double grade : this.CourseOutcomes.values()) {
        sum += grade;
      }
      this.TotalGrade = sum / size;
    }
  }

  // ID Getter and Setter
  public int getID() {
    return this.ID;
  }

  public void setID(int newID) {
    this.ID = newID;
  }

  // Name Getters and Setters
  public String getLegalName() {
    return this.LegalName;
  }

  public String getPreferredName() {
    return this.PreferredName;
  }

  public void setLegalName(String newLegalName) {
    this.LegalName = newLegalName;
  }

  public void setPreferredName(String newPreferredName) {
    this.PreferredName = newPreferredName;
  }

  public void setPreferredName() {
    setPreferredName("");
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

  public void addRegisteredCourse(String CourseName, String[] CourseOutcomes) {
    this.RegisteredCourses.add(new RegisteredCourse(CourseName, CourseOutcomes));
  }

  public void removeRegisteredCourse(String CourseName) {
    for (RegisteredCourse rc : this.RegisteredCourses) {
      if (rc.getCourseName() == CourseName) {
        this.RegisteredCourses.remove(rc);
      }
    }
  }
}
