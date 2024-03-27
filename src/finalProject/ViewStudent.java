package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
// import java.util.*;

import net.miginfocom.swing.MigLayout;

public class ViewStudent extends JFrame {
	
	private Student student;
	
	private StorageHandler shdl;
	private RuntimeDatabase rdb;
	
	private DefaultTableModel rcModel;
	private DefaultTableModel ecModel;
	
	private String[] rcColumns = new String[] {"Course ID", "Course Name", "Course Grade"};
	private String[] ecColumns = new String[] {"Contact ID", "Contact Name", "Home Phone", "Cell Phone", "Email"};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable registeredCoursesTable;
	private JTable emergencyContactsTable;

	/**
	 * Create the frame.
	 */
	public ViewStudent(Student s, StorageHandler shdl, RuntimeDatabase rdb) {
		student = s;
		this.shdl = shdl;
		this.rdb = rdb;
		
		rcModel = new DefaultTableModel(s.getRegisteredCourses(), rcColumns);
		ecModel = new DefaultTableModel(s.getEmergencyContacts(), ecColumns);
		
		setTitle("Student Viewer");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1024, 640);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);
		
		JMenuItem mntmEditStudent = new JMenuItem("Edit Student Info");
		mnActions.add(mntmEditStudent);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnActions.add(mntmExit);
		
		JMenu mnRC = new JMenu("Registered Courses");
		menuBar.add(mnRC);
		
		JMenuItem mntmAddRC = new JMenuItem("Add Registered Course");
		mntmAddRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRegisteredCourseWindow();
			}
		});
		mnRC.add(mntmAddRC);
		
		JMenuItem mntmDelRC = new JMenuItem("Remove Registered Course");
		mntmDelRC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeRegisteredCourse();
			}
		});
		mnRC.add(mntmDelRC);
		
		JMenu mnEC = new JMenu("Emergency Contacts");
		menuBar.add(mnEC);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Emergency Contact");
		mnEC.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Remove Emergency Contact");
		mnEC.add(mntmNewMenuItem_1);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("About");
		mnHelp.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[189px][247px][49px][10px][][1px][9px][189px][247px][49px]", "[25px][253px][2px][25px][253px]"));
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentId, "cell 2 0");
		
		JLabel lblStudentNumber = new JLabel(student.getID());
		lblStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentNumber, "cell 4 0");
		
		JPanel studentInfoPanel = new JPanel();
		studentInfoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(studentInfoPanel, "cell 0 1 10 1,grow");
		studentInfoPanel.setLayout(new MigLayout("wrap", "grow, fill", "grow, fill"));
		
		JPanel LNM = new JPanel();
		LNM.setBorder(null);
		studentInfoPanel.add(LNM, "cell 1 1,push ,grow");
		LNM.setLayout(new MigLayout("", "[grow][]", "[][][]"));
		
		JLabel lblLegalName = new JLabel("Legal Name:");
		lblLegalName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNM.add(lblLegalName, "flowx,cell 0 0");
		
		JLabel lblLNM = new JLabel(student.getLegalName());
		lblLNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNM.add(lblLNM, "cell 0 1");
		
		JPanel PNM = new JPanel();
		PNM.setBorder(null);
		studentInfoPanel.add(PNM, "cell 2 1,push ,grow");
		PNM.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblPreferredName = new JLabel("Preferred Name:");
		lblPreferredName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNM.add(lblPreferredName, "flowx,cell 0 0");
		
		JLabel lblPNM = new JLabel(student.getPreferredName());
		lblPNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNM.add(lblPNM, "cell 0 1");
		
		JPanel GND = new JPanel();
		GND.setBorder(null);
		studentInfoPanel.add(GND, "cell 3 1,push ,grow");
		GND.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GND.add(lblGender, "flowx,cell 0 0");
		
		JLabel lblGND = new JLabel(student.getGender());
		lblGND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GND.add(lblGND, "cell 0 1");
		
		JPanel PNS = new JPanel();
		PNS.setBorder(null);
		studentInfoPanel.add(PNS, "cell 1 2,push ,grow");
		PNS.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblPronouns = new JLabel("Pronouns:");
		lblPronouns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNS.add(lblPronouns, "flowx,cell 0 0");
		
		JLabel lblPNS = new JLabel(student.getPronouns());
		lblPNS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNS.add(lblPNS, "cell 0 1");
		
		JPanel EML = new JPanel();
		EML.setBorder(null);
		studentInfoPanel.add(EML, "cell 2 2,push ,grow");
		EML.setLayout(new MigLayout("", "[]", "[][]"));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EML.add(lblEmail, "flowx,cell 0 0");
		
		JLabel lblEML = new JLabel(student.getEmail());
		lblEML.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EML.add(lblEML, "cell 0 1");
		
		JPanel DOB = new JPanel();
		DOB.setBorder(null);
		studentInfoPanel.add(DOB, "cell 3 2,push ,grow");
		DOB.setLayout(new MigLayout("", "[]", "[][]"));
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth:");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DOB.add(lblDateOfBirth, "flowx,cell 0 0");
		
		JLabel lblDOB = new JLabel(student.getDoB());
		lblDOB.setFont(new Font("Tahoma", Font.PLAIN, 15));
		DOB.add(lblDOB, "cell 0 1");
		
		JScrollPane rcPane = new JScrollPane();
		contentPane.add(rcPane, "cell 0 4 3 1,grow");
		
		registeredCoursesTable = new JTable();
		registeredCoursesTable.setFillsViewportHeight(true);
		registeredCoursesTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		registeredCoursesTable.getTableHeader().setReorderingAllowed(false);
		registeredCoursesTable.setModel(rcModel);
		registeredCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rcPane.setViewportView(registeredCoursesTable);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_1, "cell 4 4,alignx center,growy");
		
		JScrollPane ecPane = new JScrollPane();
		contentPane.add(ecPane, "cell 7 4 3 1,grow");
		
		emergencyContactsTable = new JTable();
		emergencyContactsTable.setFillsViewportHeight(true);
		emergencyContactsTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emergencyContactsTable.getTableHeader().setReorderingAllowed(false);
		emergencyContactsTable.setModel(ecModel);
		emergencyContactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ecPane.setViewportView(emergencyContactsTable);
		
		registeredCoursesTable.setDefaultEditor(Object.class, null);
		emergencyContactsTable.setDefaultEditor(Object.class, null);
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 2 10 1,growx,aligny top");
		
		JLabel lblRegisteredCourses = new JLabel("Registered Courses");
		lblRegisteredCourses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblRegisteredCourses, "cell 0 3,alignx left,aligny top");
		
		JLabel lblEmergencyContacts = new JLabel("Emergency Contacts");
		lblEmergencyContacts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblEmergencyContacts, "cell 7 3,growx,aligny top");
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentInformation, "cell 0 0,growx,aligny top");
		
		JButton editStudentInfo = new JButton("Edit");
		contentPane.add(editStudentInfo, "cell 9 0,alignx right,growy");
	}
	
	public void addRegisteredCourseWindow() {
		AddRegisteredCourse addRegisteredCourseWindow = new AddRegisteredCourse(student.getID(), shdl, rdb, rcModel);
		addRegisteredCourseWindow.setVisible(true);
	}
	
	public void removeRegisteredCourse() {
		int col = 0;
		int row = registeredCoursesTable.getSelectedRow();
		
		try {
			String courseID = (String) registeredCoursesTable.getValueAt(row, col);
			shdl.deleteCourseFromRegisteredCourses(courseID);
			rcModel.removeRow(row);
			student.removeRegisteredCourse(courseID);
		} catch (Exception e2) {
			System.out.println("An Error Occurred.");
			e2.printStackTrace();
		}
		
	}
}
