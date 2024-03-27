package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
// import java.util.*;

import net.miginfocom.swing.MigLayout;

public class AddRegisteredCourse extends JFrame {
	
	private String studentID;
	private StorageHandler shdl;
	private RuntimeDatabase rdb;
	private DefaultTableModel dtm;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField courseID;
	private JTextField courseName;

	/**
	 * Create the frame.
	 */
	public AddRegisteredCourse(String ID, StorageHandler shdl, RuntimeDatabase rdb, DefaultTableModel dtm) {
		this.studentID = ID;
		this.shdl = shdl;
		this.rdb = rdb;
		this.dtm = dtm;
		setTitle("Add Registered Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 215);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow][grow]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,push ,grow");
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
		contentPane.add(buttonPanel, "cell 0 1,grow");
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
		Student student = rdb.getStudentByID(studentID);
		String idString = courseID.getText();
		String nameString = courseName.getText();
		student.addRegisteredCourse(idString, nameString);
		dtm.addRow(new String[] {idString, nameString, "0.0"});
		try {
			shdl.addRegisteredCourseToDatabase(studentID, idString, nameString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispose();
	}

}
