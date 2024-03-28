package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
// import java.util.*;

import net.miginfocom.swing.MigLayout;

public class AddRegisteredCourse extends JFrame {
	
	private Student student;
	private StorageHandler shdl;
	private RuntimeDatabase rdb;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseID;
	private JTextField courseName;
	private JLabel status;

	/**
	 * Create the frame.
	 */
	public AddRegisteredCourse(Student s, StorageHandler shdl) {
		this.student = s;
		this.shdl = shdl;
		
		setTitle("Add Registered Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 256);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[][][grow][grow]"));
		
		JLabel lblRequired = new JLabel("* - Required Field");
		lblRequired.setForeground(Color.RED);
		lblRequired.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblRequired, "cell 0 0");
		
		status = new JLabel(" ");
		status.setForeground(Color.RED);
		contentPane.add(status, "cell 0 2");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 1,push ,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		
		JLabel lblCourseID = new JLabel("Course ID");
		lblCourseID.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblCourseID, "flowx,cell 0 0");
		
		courseID = new JTextField();
		panel.add(courseID, "cell 0 1,growx");
		courseID.setColumns(10);
		
		JLabel lblReq = new JLabel("*");
		lblReq.setForeground(Color.RED);
		lblReq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblReq, "cell 0 0");
		
		JLabel lblCourseName = new JLabel("Course Name");
		lblCourseName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblCourseName, "flowx,cell 0 3");
		
		courseName = new JTextField();
		courseName.setColumns(10);
		panel.add(courseName, "cell 0 4,growx");
		
		JLabel lblReq_1 = new JLabel("*");
		lblReq_1.setForeground(Color.RED);
		lblReq_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblReq_1, "cell 0 3");
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, "cell 0 3,grow");
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCourse();
			}
		});
		buttonPanel.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(btnCancel);
	}
	
	private void addCourse() {
		String idString = courseID.getText();
		String nameString = courseName.getText();
		
		if (idString.isBlank() || nameString.isBlank()) {
			status.setText("All of the required fields must be filled out.");
			return;
		}
		
		try {
			student.addRegisteredCourse(idString, nameString);
			shdl.addRegisteredCourseToDatabase(student.getID(), student.getRegisteredCourseByID(idString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispose();
	}

}
