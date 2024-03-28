package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
//import java.text.*;
//import java.util.*;

import net.miginfocom.swing.MigLayout;

public class EditStudent extends JFrame {
	
	private StorageHandler shdl;
	
	private Student student;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inputEmail;
	private JTextField inputFirstName;
	private JTextField inputLastName;
	private JTextField inputPrefFirst;
	private JTextField inputPrefLast;
	private JComboBox<String> genderSelect;
	private JComboBox<String> pronounsSelect;
	private JLabel status;
	
	/**
	 * Create the frame.
	 */
	public EditStudent(Student s, StorageHandler shdl) {

		this.shdl = shdl;
		this.student = s;
		
		String pName = student.getPreferredName();
		
		String[] splitLName = student.getLegalName().split(" ");
		String[] splitPName = new String[2];
		
		if (pName.contains(" ")) {
			String[] temp = pName.split("\\s+", 2);
			splitPName[0] = temp[0];
			splitPName[1] = temp.length > 1 ? temp[1] : "";
		} else {
			splitPName[0] = pName;
			splitPName[1] = "";
		}
			
		
		setTitle("Edit Student");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 394);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new MigLayout("", "[grow]", "[][][grow][]"));
		
		JLabel lblRequired = new JLabel("* - Required Field");
		lblRequired.setForeground(Color.RED);
		lblRequired.setFont(new Font("Tahoma", Font.PLAIN, 10));
		mainPanel.add(lblRequired, "cell 0 0");
		
		JPanel panel = new JPanel();
		mainPanel.add(panel, "cell 0 2,push ,grow");
		panel.setLayout(new BorderLayout(0, 0));
		
		status = new JLabel(" ");
		status.setForeground(Color.RED);
		panel.add(status, BorderLayout.WEST);
		
		JPanel inputPanel = new JPanel();
		mainPanel.add(inputPanel, "cell 0 1,push ,grow");
		inputPanel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][][][][][][][grow]"));
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblEmail, "flowx,cell 0 0");
		
		JLabel lblReq = new JLabel("*");
		lblReq.setForeground(Color.RED);
		lblReq.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblReq, "cell 0 0");
		
		inputEmail = new JTextField(student.getEmail());
		inputPanel.add(inputEmail, "cell 0 1 2 1,growx");
		inputEmail.setColumns(10);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblFirstName, "flowx,cell 0 3");
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblLastName, "flowx,cell 1 3");
		
		inputFirstName = new JTextField(splitLName[0]);
		inputPanel.add(inputFirstName, "cell 0 4,growx");
		inputFirstName.setColumns(10);
		
		inputLastName = new JTextField(splitLName[1]);
		inputPanel.add(inputLastName, "cell 1 4,growx");
		inputLastName.setColumns(10);
		
		JLabel lblPreferredFirstName = new JLabel("Preferred First Name");
		lblPreferredFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblPreferredFirstName, "cell 0 6");
		
		JLabel lblPreferredLastName = new JLabel("Preferred Last Name");
		lblPreferredLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		inputPanel.add(lblPreferredLastName, "cell 1 6");
		
		inputPrefFirst = new JTextField(splitPName[0]);
		inputPanel.add(inputPrefFirst, "cell 0 7,growx");
		inputPrefFirst.setColumns(10);
		
		inputPrefLast = new JTextField(splitPName[1]);
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
		genderSelect.setSelectedItem(student.getGender());
		inputPanel.add(genderSelect, "cell 0 10,growx");
		
		pronounsSelect = new JComboBox<>();
		pronounsSelect.setModel(new DefaultComboBoxModel<String>(new String[] {"", "He/Him", "She/Her", "They/Them", "Other", "Prefer Not To Say"}));
		pronounsSelect.setSelectedItem(student.getPronouns());
		inputPanel.add(pronounsSelect, "cell 1 10,growx");
		
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
				updateStudent();
			}
		});
		buttonPanel.add(btnConfirm);
		buttonPanel.add(btnCancel);
	}
	
	private void updateStudent() {
		String email = inputEmail.getText().strip();
		String firstName = inputFirstName.getText().strip();
		String lastName = inputLastName.getText().strip();
		String prefFirst = inputPrefFirst.getText().strip();
		String prefLast = inputPrefLast.getText().strip();
		String gender = genderSelect.getSelectedItem().toString().strip();
		String pronouns = pronounsSelect.getSelectedItem().toString().strip();
		
		if (email.isBlank() || firstName.isBlank() || lastName.isBlank() || gender.isBlank()) {
			status.setText("All of the required fields must be filled out.");
			return;
		}
		
		String legalName = String.format("%s %s", firstName, lastName);
		String preferredName = String.format("%s %s", prefFirst, prefLast);
		
		try {
			student.setAllEditable(email, legalName, preferredName, gender, pronouns);
			shdl.updateStudentInfo(student);
		} catch (Exception e) {
			System.out.println("An Error Occurred.");
			e.printStackTrace();
			return;
		}
		dispose();
	}
	
}
