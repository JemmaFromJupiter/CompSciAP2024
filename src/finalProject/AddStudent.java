package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.text.*;
import java.util.*;

import com.toedter.calendar.JCalendar;

import net.miginfocom.swing.MigLayout;

public class AddStudent extends JFrame {
	
	private Random random = new Random();
	private DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
	private DateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
	
	private StorageHandler shdl;
	private RuntimeDatabase rdb;
	
	private Student newStudent;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JTextField inputFirstName;
	private JTextField inputLastName;
	private JTextField inputPrefFirst;
	private JTextField inputPrefLast;
	private JCalendar dobCalendar;
	private JComboBox<String> genderSelect;
	private JComboBox<String> pronounsSelect;
	private JLabel status;
	
	/**
	 * Create the frame.
	 */
	public AddStudent(StorageHandler shdl, RuntimeDatabase rdb) {
		
		this.shdl = shdl;
		this.rdb = rdb;
		
		setTitle("Add Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[]", "[][][]"));
		
		JLabel lblRequired = new JLabel("* - Required Field");
		lblRequired.setForeground(Color.RED);
		lblRequired.setFont(new Font("Tahoma", Font.PLAIN, 10));
		mainPanel.add(lblRequired, "cell 0 0");
		
		status = new JLabel(" ");
		status.setForeground(Color.RED);
		mainPanel.add(status, "cell 0 2");
		
		JPanel inputPanel = new JPanel();
		mainPanel.add(inputPanel, "cell 0 1 2 1,push ,grow");
		inputPanel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][][][][][][grow]"));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblEmail, "flowx,cell 0 0");
		
		JLabel lblReq = new JLabel("*");
		lblReq.setForeground(Color.RED);
		lblReq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq, "cell 0 0");
		
		inputEmail = new JTextField();
		inputPanel.add(inputEmail, "cell 0 1 2 1,growx");
		inputEmail.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblFirstName, "flowx,cell 0 3");
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblLastName, "flowx,cell 1 3");
		
		inputFirstName = new JTextField();
		inputPanel.add(inputFirstName, "cell 0 4,growx");
		inputFirstName.setColumns(10);
		
		inputLastName = new JTextField();
		inputPanel.add(inputLastName, "cell 1 4,growx");
		inputLastName.setColumns(10);
		
		JLabel lblPreferredFirstName = new JLabel("Preferred First Name");
		lblPreferredFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblPreferredFirstName, "cell 0 6");
		
		JLabel lblPreferredLastName = new JLabel("Preferred Last Name");
		lblPreferredLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblPreferredLastName, "cell 1 6");
		
		inputPrefFirst = new JTextField();
		inputPanel.add(inputPrefFirst, "cell 0 7,growx");
		inputPrefFirst.setColumns(10);
		
		inputPrefLast = new JTextField();
		inputPanel.add(inputPrefLast, "cell 1 7,growx");
		inputPrefLast.setColumns(10);
		
		JLabel lblReq1 = new JLabel("*");
		lblReq1.setForeground(Color.RED);
		lblReq1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq1, "cell 1 3");
		
		JLabel lblReq0 = new JLabel("*");
		lblReq0.setForeground(Color.RED);
		lblReq0.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq0, "cell 0 3");
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblGender, "flowx,cell 0 9");
		
		JLabel lblReq0_1 = new JLabel("*");
		lblReq0_1.setForeground(Color.RED);
		lblReq0_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq0_1, "cell 0 9");
		
		JLabel lblPronouns = new JLabel("Pronouns");
		lblPronouns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblPronouns, "cell 1 9");
		
		genderSelect = new JComboBox<>();
		genderSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Male", "Female", "Other", "Prefer Not To Say"}));
		inputPanel.add(genderSelect, "cell 0 10,growx");
		
		pronounsSelect = new JComboBox<>();
		pronounsSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"", "He/Him", "She/Her", "They/Them", "Other", "Prefer Not To Say"}));
		inputPanel.add(pronounsSelect, "cell 1 10,growx");
		
		JLabel lblDateOfBirth_1 = new JLabel("Date of Birth");
		lblDateOfBirth_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblDateOfBirth_1, "flowx,cell 0 12");
		
		dobCalendar = new JCalendar();
		inputPanel.add(dobCalendar, "cell 0 13 2 1,grow");
		
		JLabel lblReq0_1_1_1 = new JLabel("*");
		lblReq0_1_1_1.setForeground(Color.RED);
		lblReq0_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq0_1_1_1, "cell 0 12");
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(240, 240, 240));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addStudent();
			}
		});
		buttonPanel.add(btnConfirm);
		buttonPanel.add(btnCancel);
	}
	
	private void addStudent() {
		String studentID = String.format("%9s", Integer.toString(random.nextInt(1, 1000000000))).replace(" ", "0");
		String email = inputEmail.getText().strip();
		String firstName = inputFirstName.getText().strip();
		String lastName = inputLastName.getText().strip();
		String prefFirstName = inputPrefFirst.getText().strip();
		String prefLastName = inputPrefLast.getText().strip();
		String gender = genderSelect.getSelectedItem().toString().strip();
		String pronouns = pronounsSelect.getSelectedItem().toString().strip();
		String dob = parseDateToString().strip();
		
		if (email.isBlank() || firstName.isBlank() || lastName.isBlank() || gender.isBlank()) {
			status.setText("All of the required fields must be filled out.!");
			return;
		}
			
		try {
			newStudent = new Student(
					studentID,
					firstName + " " + lastName,
					prefFirstName + " " + prefLastName,
					gender,
					pronouns,
					email,
					dob);
				
			rdb.append(newStudent);
			shdl.addStudentToDatabase(newStudent);
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		dispose();
	}
	
	private String parseDateToString() {
		try {
			return formatter1.format(formatter.parse(dobCalendar.getDate().toString()));
		} catch (Exception dtE) {
			dtE.printStackTrace();
			return "01/01/2000";
		}
	}
	
}
