/**
 * This file does not matter in the scope of the final goal of the project.
 * Mostly just for testing and nice display for testing.
 */

package finalProject;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class PopulateTableProgress extends JFrame {
	
	private static final Random random = new Random();
	// Random first names array
	private static String[] testFirstNames = {
	    "Emma", "Liam", "Olivia", "Noah", "Ava", 
	    "William", "Sophia", "James", "Isabella", "Oliver",
	    "Mia", "Benjamin", "Charlotte", "Elijah", "Amelia"
	};

	// Random last names array
	private static String[] testLastNames = {
	    "Smith", "Johnson", "Williams", "Brown", "Jones",
	    "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
	    "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
	};
	
	private static String[] testGenders = {
			"Male", "Female"
	};
	
	private static ArrayList<Student> students;
	private static Integer numStudents;
	private static StorageHandler shdl;
	private static RuntimeDatabase rdb;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static ProgressWorker progWorker;
	private static JLabel lblNewLabel;
	private static JLabel status;
	
	public PopulateTableProgress(Integer Students, StorageHandler hdl, RuntimeDatabase db) {
		numStudents = Students;
		shdl = hdl;
		rdb = db;
		
		run();
	}
	
	private void run() {
		setTitle("Populating Database...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		lblNewLabel = new JLabel("Randomizing Data...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 0 1,push ,grow");
		
		status = new JLabel("New label");
		contentPane.add(status, "cell 0 2");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(progressBar, "cell 0 4,push ,growx");
		
		progWorker = new ProgressWorker(progressBar);
	}
	
	public static void showDialog(Integer numStudents, StorageHandler shdl, RuntimeDatabase rdb) {
		PopulateTableProgress ptp = new PopulateTableProgress(numStudents, shdl, rdb);
		ptp.setVisible(true);
		progWorker.execute();
	}
	
	private static class ProgressWorker extends SwingWorker<Void, Integer> {
		private static JProgressBar progress;
		
		public ProgressWorker(JProgressBar p) {
			progress = p;
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			students = new ArrayList<>();
			int randomNameOrigin = 0;
			int randomNameLimit = 15;
			
			for (int i = 0; i < numStudents; i++) {
				Student newStudent = new Student(String.format("%9s", Integer.toString(random.nextInt(1, 1000000000))).replace(" ", "0"), testFirstNames[random.nextInt(randomNameOrigin, randomNameLimit)] + " " + testLastNames[random.nextInt(randomNameOrigin, randomNameLimit)], "", testGenders[random.nextInt(0, 2)], "", "name@domain.com", "01/01/2000");
				students.add(newStudent);
				status.setText(String.format("Generated Data For %s...", newStudent.getLegalName()));
			}
			
			progressBar.setMaximum(students.size());
			lblNewLabel.setText("Populating Database...");
			
			for (int i = students.size() - 1; i >= 0; i--) {
				try {
					Student s = students.get(i);
					rdb.append(s);
					shdl.addStudentToDatabase(s);
					for (int j = 0; j < random.nextInt(4, 20); j++) {
						Student.RegisteredCourse rc = new Student.RegisteredCourse("TSTCRS" + (random.nextInt(0, (int) Math.pow(randomNameLimit, 2))), "Test Course", random.nextInt(1, 5), random.nextDouble(50.0, 100.0));
						s.addRegisteredCourse(rc.getCourseID(), rc.getCourseName(), rc.getCourseCredits(), rc.getTotalGrade());
						shdl.addRegisteredCourseToDatabase(s.getID(), rc);
						status.setText(String.format("Registered Course %s For %s...", rc.getCourseName(), s.getLegalName()));
					}
					
					for (int j = 0; j < random.nextInt(2, 6); j++) {
						Student.EmergencyContact ec = new Student.EmergencyContact(Integer.toHexString(random.nextInt()), "Test Contact", "", "", "");
						s.addEmergencyContact(ec.getContactID(), ec.getContactName(), ec.getContactHome(), ec.getContactCell(), ec.getContactEmail());
						shdl.addEmergencyContactToDatabase(s.getID(), ec);
						status.setText(String.format("Registered Emergency Contact %s For %s...", ec.getContactName(), s.getLegalName()));
					}
					
					
					
					final int prog = (int) ((100L * (students.size() - i)) / 100);
					
					if (prog >= (int) students.size() * 0.85)
						lblNewLabel.setText("Almost There...");
					
					publish(prog);
				} catch (Exception tE) {
					System.out.println("Something went wrong...");
					tE.printStackTrace();
					break;
				}
			}
			return null;
		}
		
		@Override
		protected void process(List<Integer> chunks) {
			progress.setValue(chunks.get(chunks.size() - 1));
			super.process(chunks);
		}
		
		@Override
		protected void done() {
			lblNewLabel.setText("Finished!");
			status.setText(String.format("Successfully Added %d Students To The Database.", students.size()));
			progress.setValue(rdb.size());
		}
	}

}
