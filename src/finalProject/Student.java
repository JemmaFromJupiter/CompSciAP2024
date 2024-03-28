package finalProject;

import java.util.regex.Pattern;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Student {
  private Pattern dobPattern = Pattern.compile("^(0[1-9]|1[1-9]|[12][0-9]|3[0-1])/(0[1-9]|1[0-2])/(\\d{4})$");

  private String ID;
  private String LegalName;
  private String PreferredName;
  private String Email;
  private String Gender;
  private String Pronouns;
  private String DoB;
  private ArrayList<RegisteredCourse> RegisteredCourses;
  private ArrayList<EmergencyContact> EmergencyContacts;

  public Student(String ID, String LegalName, String PreferredName,
      String Gender, String Pronouns, String Email, String DoB) {
    this.setID(ID);
    this.setLegalName(LegalName);
    this.setPreferredName(PreferredName);
    this.setGender(Gender);
    this.setPronouns(Pronouns);
    this.setEmail(Email);
    this.setDoB(DoB);
    this.RegisteredCourses = new ArrayList<>();
    this.EmergencyContacts = new ArrayList<>();
  }

  static class EmergencyContact {
    private String ContactID;
    private String ContactName;
    private String ContactHome;
    private String ContactCell;
    private String ContactEmail;

    public EmergencyContact(String ContactID, String ContactName, String ContactHome, String ContactCell,
        String ContactEmail) {
      this.setContactID(ContactID);
      this.setContactName(ContactName);
      this.setContactHome(ContactHome);
      this.setContactCell(ContactCell);
      this.setContactEmail(ContactEmail);
    }

    // setters and getRegisteredCourses
    public String getContactID() {
      return this.ContactID;
    }

    public void setContactID(String newID) {
      this.ContactID = newID;
    }

    public String getContactName() {
      return this.ContactName;
    }

    public void setContactName(String newName) {
      this.ContactName = newName;
    }

    public String getContactHome() {
      return this.ContactHome;
    }

    public void setContactHome(String newHome) {
      this.ContactHome = newHome;
    }

    public String getContactCell() {
      return this.ContactCell;
    }

    public void setContactCell(String newCell) {
      this.ContactCell = newCell;
    }

    public String getContactEmail() {
      return this.ContactEmail;
    }

    public void setContactEmail(String newEmail) {
      this.ContactEmail = newEmail;
    }
    
    public String[] asArray() {
    	return new String[] {this.getContactID(), this.getContactName(), this.getContactCell(), this.getContactHome(), this.getContactEmail()};
    }

    public String toString() {
      return String.format("Emergency Contact: [%s, %s, %s, %s]", this.getContactName(), this.getContactHome(),
          this.getContactCell(), this.getContactEmail());
    }
  }

  static class RegisteredCourse {
    private String CourseID;
    private String CourseName;
    private Double TotalGrade;
    private Integer Credits;

    public RegisteredCourse(String CourseID, String CourseName, Integer Credits) {
      this.CourseID = CourseID;
      this.CourseName = CourseName;
      this.TotalGrade = 0.0;
      this.Credits = Credits;
    }

    public RegisteredCourse(String CourseID, String CourseName, Integer Credits, Double CourseGrade) {
      this.CourseID = CourseID;
      this.CourseName = CourseName;
      this.TotalGrade = CourseGrade;
      this.Credits = Credits;
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
    
    public Integer getCourseCredits() {
    	return Credits;
    }

    // Total Grade Utils
    public Double getTotalGrade() {
      return this.TotalGrade;
    }

    public void setTotalGrade(Double newGrade) {
      this.TotalGrade = newGrade;
    }
    
    public Double calculateGradePoints() {
    	return Credits * TotalGrade;
    }
    
    public String[] asArray() {
    	return new String[] {this.getCourseID(), this.getCourseName(), Double.toString(this.getTotalGrade())};
    }

    public String toString() {
      return String.format("Registered Course: [%s, %s, %.2f]", this.getCourseID(), this.getCourseID(),
          this.getTotalGrade());
    }

  }

  // setter for all editable values after they've been stored.
  public void setAllEditable(String email, String legalName, String preferredName, String gender, String pronouns) {
	  setEmail(email);
	  setLegalName(legalName);
	  setPreferredName(preferredName);
	  setGender(gender);
	  setPronouns(pronouns);
  }
  
  // ID Getter and Setter
  public String getID() {
    return this.ID;
  }

  private void setID(String newID) {
    this.ID = newID;
  }

  // Name Getters and Setters
  public String getLegalName() {
	  return this.LegalName;
  }

  public String getPreferredName() {
	  if (this.PreferredName != null)
		  return this.PreferredName;
	  else
		  return "None";
  }

  public void setLegalName(String newLegalName) {
    this.LegalName = newLegalName;
  }

  public void setPreferredName(String newPreferredName) {
    this.PreferredName = newPreferredName;
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

  public String[][] getRegisteredCourses() {
	String[][] registeredCourses = new String[this.RegisteredCourses.size()][3];
	
	int i = 0;
	for (RegisteredCourse rc : this.RegisteredCourses) {
		registeredCourses[i] = rc.asArray();
		i++;
	}
	
	return registeredCourses;
  }
  
  public RegisteredCourse getRegisteredCourseByID(String courseID) {
	  for (RegisteredCourse rc : RegisteredCourses) {
		  if (rc.getCourseID() == courseID)
			  return rc;
	  }
	  return null;
  }

  public void addRegisteredCourse(String CourseID, String CourseName, Integer Credits) {
    this.RegisteredCourses.add(new RegisteredCourse(CourseID, CourseName, Credits));
  }

  public void addRegisteredCourse(String CourseID, String CourseName, Integer Credits, Double CourseGrade) {
    this.RegisteredCourses.add(new RegisteredCourse(CourseID, CourseName, Credits, CourseGrade));
  }

  public void removeRegisteredCourse(String CourseID) {
    for (RegisteredCourse rc : this.RegisteredCourses) {
      if (rc.getCourseID().equals(CourseID)) {
        this.RegisteredCourses.remove(rc);
        return;
      }
    }
  }
  
  public void updateCourseGrade(String courseID, Double grade) {
	  RegisteredCourse rc;
	  if (!courseRegistered(courseID))
		  return;
	  
	  rc = getRegisteredCourseByID(courseID);
	  rc.setTotalGrade(grade);
  }

  public boolean courseRegistered(String CourseID) {
    for (RegisteredCourse rc : this.RegisteredCourses) {
      if (rc.getCourseID().equals(CourseID))
        return true;
    }
    return false;
  }

  public String[][] getEmergencyContacts() {
	  	String[][] contacts = new String[this.EmergencyContacts.size()][3];
		
		int i = 0;
		for (EmergencyContact ec : this.EmergencyContacts) {
				contacts[i] = ec.asArray();
				i++;
		}
		
		return contacts;
  }
  
  public EmergencyContact getEmergencyContactByID(String contactID) {
	  for (EmergencyContact ec : EmergencyContacts) {
		  if (ec.getContactID() == contactID)
			  return ec;
	  }
	  return null;
  }

  public void addEmergencyContact(String ContactID, String ContactName, String ContactHome, String ContactCell,
      String ContactEmail) {
    this.EmergencyContacts.add(new EmergencyContact(ContactID, ContactName, ContactHome, ContactCell, ContactEmail));
  }

  public void removeEmergencyContact(String ContactID) {
    for (EmergencyContact ec : this.EmergencyContacts) {
      if (ec.getContactID().equals(ContactID)) {
        this.EmergencyContacts.remove(ec);
        return;
      }
    }
  }

  public boolean emergencyContactRegistered(String ContactID) {
    for (EmergencyContact ec : this.EmergencyContacts) {
      // System.out.println(ec.getContactID() == ContactID);
      if (ec.getContactID().equals(ContactID))
        return true;
    }
    return false;
  }
  
  public double calculateAverageGrade() {
	  if (RegisteredCourses.isEmpty())
		  return 0.00;
	  
	  Double sum = 0.0;
	  for (RegisteredCourse rc : RegisteredCourses) {
		  sum += rc.getTotalGrade();
	  }
	  return sum / RegisteredCourses.size();
  }
  
  public Double calculateGPA() {
	  if (RegisteredCourses.isEmpty())
		  return 0.00;
	  
	  Double totalScore = 0.0;
	  Integer totalCredits = 0;
	  for (RegisteredCourse rc : RegisteredCourses) {
		  totalScore += rc.calculateGradePoints();
		  totalCredits += rc.getCourseCredits();
	  }
	  
	  return (double) ((totalScore / totalCredits)/100)*4;
  }

  public Object[] asArray() {
    return new Object[] { this.getID(), this.getLegalName(),
        this.getPreferredName(), this.getGender(), this.getDoB() };
  }

  public String toString() {
    return String.format("[%s, %s, %s, %s, %s, %s, %s, %s, %s]", this.getID(), this.getLegalName(),
        this.getPreferredName(),
        this.getGender(), this.getPronouns(), this.getEmail(), this.getDoB(), this.getRegisteredCourses(),
        this.getEmergencyContacts());
  }
}
