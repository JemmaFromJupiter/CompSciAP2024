package finalProject;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
// import java.util.*;

import net.miginfocom.swing.MigLayout;

public class ViewStudent extends JFrame {
	
	private final int isFocused = JComponent.WHEN_FOCUSED;
	private final int inFocusedWindow = JComponent.WHEN_IN_FOCUSED_WINDOW;
	
	private KeyStroke ctrlR = KeyStroke.getKeyStroke("control R");
	private final String REFRESH = "rfsh";
	
	private KeyStroke del = KeyStroke.getKeyStroke("DELETE");
	private KeyStroke bksp = KeyStroke.getKeyStroke("BACK_SPACE");
	private final String REMOVE = "table-";
	
	private KeyStroke ctrlQ = KeyStroke.getKeyStroke("control Q");
	private final String EXIT = "exit";
	
	private Student student;
	
	private StorageHandler shdl;
	
	private DefaultTableModel rcModel;
	private DefaultTableModel ecModel;
	
	private String[] rcColumns = new String[] {"Course ID", "Course Name", "Course Grade"};
	private String[] ecColumns = new String[] {"Contact ID", "Contact Name", "Home Phone", "Cell Phone", "Email"};

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable registeredCoursesTable;
	private JTable emergencyContactsTable;
	private JLabel lblLNM;
	private JLabel lblPNM;
	private JLabel lblGND;
	private JLabel lblPNS;
	private JLabel lblEML;
	private JLabel lblGPA;
	private JLabel lblAveGrade;

	/**
	 * Create the frame.
	 */
	public ViewStudent(Student s, StorageHandler shdl) {
		student = s;
		this.shdl = shdl;
		
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
		
		JMenuItem mntmRefreshAll = new JMenuItem(String.format("Refresh All%50s", "CTRL+R"));
		mntmRefreshAll.addActionListener(new RefreshAllAction());
		mnActions.add(mntmRefreshAll);
		
		JMenuItem mntmExit = new JMenuItem(String.format("Exit Window%47s", "CTRL+Q"));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		mnActions.add(mntmExit);
		
		JMenu mnStudentInfo = new JMenu("Student Info");
		menuBar.add(mnStudentInfo);
		
		JMenuItem mntmEditStudent = new JMenuItem("Edit Student Info");
		mntmEditStudent.addActionListener(new EditStudentAction());
		mnStudentInfo.add(mntmEditStudent);
		
		JMenuItem mntmRefreshInfo = new JMenuItem("Refresh Student Info");
		mntmRefreshInfo.addActionListener(new RefreshInfoAction());
		mnStudentInfo.add(mntmRefreshInfo);
		
		JMenu mnRC = new JMenu("Registered Courses");
		menuBar.add(mnRC);
		
		JMenuItem mntmAddRC = new JMenuItem("Add Registered Course");
		mntmAddRC.addActionListener(new AddCourseAction());
		mnRC.add(mntmAddRC);
		
		JMenuItem mntmDelRC = new JMenuItem(String.format("Remove Registered Course%10s", "DEL"));
		mntmDelRC.addActionListener(new RemoveCourseAction());
		mnRC.add(mntmDelRC);
		
		JMenuItem mntmRefreshCourses = new JMenuItem("Refresh Courses");
		mntmRefreshCourses.addActionListener(new RefreshCoursesAction());
		mnRC.add(mntmRefreshCourses);
		
		JMenu mnEC = new JMenu("Emergency Contacts");
		menuBar.add(mnEC);
		
		JMenuItem mntmAddEC = new JMenuItem("Add Emergency Contact");
		mntmAddEC.addActionListener(new AddContactAction());
		mnEC.add(mntmAddEC);
		
		JMenuItem mntmDelEC = new JMenuItem(String.format("Remove Emergency Contact%10s", "DEL"));
		mntmDelEC.addActionListener(new RemoveContactAction());
		mnEC.add(mntmDelEC);
		
		JMenuItem mntmRefreshContacts = new JMenuItem("Refresh Contacts");
		mntmRefreshContacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RefreshContactsAction().actionPerformed(e);
			}
		});
		mnEC.add(mntmRefreshContacts);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[189px][247px][49px][10px][1px][9px][189px][247px][49px,grow]", "[25px][253px][2px][25px][253px,grow]"));
		
		JLabel lblStudentId = new JLabel("Student ID:");
		lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentId, "cell 2 0");
		
		JLabel lblStudentNumber = new JLabel(student.getID());
		lblStudentNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentNumber, "cell 5 0");
		
		JPanel studentInfoPanel = new JPanel();
		studentInfoPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(studentInfoPanel, "cell 0 1 12 1,grow");
		studentInfoPanel.setLayout(new MigLayout("wrap", "grow, fill", "grow, fill"));
		
		JPanel LNM = new JPanel();
		LNM.setBorder(null);
		studentInfoPanel.add(LNM, "cell 1 1,push ,grow");
		LNM.setLayout(new MigLayout("", "[grow][]", "[][][]"));
		
		JLabel lblLegalName = new JLabel("Legal Name:");
		lblLegalName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNM.add(lblLegalName, "flowx,cell 0 0");
		
		lblLNM = new JLabel(student.getLegalName());
		lblLNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LNM.add(lblLNM, "cell 0 1");
		
		JPanel PNM = new JPanel();
		PNM.setBorder(null);
		studentInfoPanel.add(PNM, "cell 2 1,push ,grow");
		PNM.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblPreferredName = new JLabel("Preferred Name:");
		lblPreferredName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNM.add(lblPreferredName, "flowx,cell 0 0");
		
		lblPNM = new JLabel(student.getPreferredName());
		lblPNM.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNM.add(lblPNM, "cell 0 1");
		
		JPanel GND = new JPanel();
		GND.setBorder(null);
		studentInfoPanel.add(GND, "cell 3 1,push ,grow");
		GND.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GND.add(lblGender, "flowx,cell 0 0");
		
		lblGND = new JLabel(student.getGender());
		lblGND.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GND.add(lblGND, "cell 0 1");
		
		JPanel PNS = new JPanel();
		PNS.setBorder(null);
		studentInfoPanel.add(PNS, "cell 1 2,push ,grow");
		PNS.setLayout(new MigLayout("", "[][]", "[][]"));
		
		JLabel lblPronouns = new JLabel("Pronouns:");
		lblPronouns.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNS.add(lblPronouns, "flowx,cell 0 0");
		
		lblPNS = new JLabel(student.getPronouns());
		lblPNS.setFont(new Font("Tahoma", Font.PLAIN, 15));
		PNS.add(lblPNS, "cell 0 1");
		
		JPanel EML = new JPanel();
		EML.setBorder(null);
		studentInfoPanel.add(EML, "cell 2 2,push ,grow");
		EML.setLayout(new MigLayout("", "[]", "[][]"));
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EML.add(lblEmail, "flowx,cell 0 0");
		
		lblEML = new JLabel(student.getEmail());
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
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_3, "flowx,cell 1 3,alignx center,growy");
		
		JLabel lblAverageGrade = new JLabel("Average Grade:");
		lblAverageGrade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblAverageGrade, "cell 1 3");
		
		JLabel lblGPAC = new JLabel("GPA:");
		lblGPAC.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblGPAC, "flowx,cell 2 3,push ,alignx center,aligny center");
		
		JLabel lblEmergencyContacts = new JLabel("Emergency Contacts");
		lblEmergencyContacts.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblEmergencyContacts, "cell 5 3,growx,aligny top");
		
		JScrollPane rcPane = new JScrollPane();
		contentPane.add(rcPane, "cell 0 4 4 1,push ,grow");
		
		registeredCoursesTable = new JTable();
		registeredCoursesTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && registeredCoursesTable.getSelectedRow() >= 0) {
					new UpdateCourseAction().actionPerformed(null);
				}
			}
		});
		registeredCoursesTable.setFillsViewportHeight(true);
		registeredCoursesTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		registeredCoursesTable.getTableHeader().setReorderingAllowed(false);
		registeredCoursesTable.setModel(rcModel);
		TableRowSorter<TableModel> rcTableSorter = new TableRowSorter<>(registeredCoursesTable.getModel());
		registeredCoursesTable.setRowSorter(rcTableSorter);
		registeredCoursesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rcPane.setViewportView(registeredCoursesTable);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_1, "cell 4 3 1 2,push ,alignx center,growy");
		
		registeredCoursesTable.setDefaultEditor(Object.class, null);
		
		JSeparator separator = new JSeparator();
		contentPane.add(separator, "cell 0 2 12 1,growx,aligny top");
		
		JLabel lblRegisteredCourses = new JLabel("Registered Courses");
		lblRegisteredCourses.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblRegisteredCourses, "cell 0 3,alignx left,aligny center");
		
		JLabel lblStudentInformation = new JLabel("Student Information");
		lblStudentInformation.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblStudentInformation, "cell 0 0,growx,aligny top");
		
		lblAveGrade = new JLabel(String.format("%.2f", student.calculateAverageGrade()));
		lblAveGrade.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblAveGrade, "cell 1 3");
		
		JScrollPane ecPane = new JScrollPane();
		contentPane.add(ecPane, "cell 5 4 3 1,push ,grow");
		
		emergencyContactsTable = new JTable();
		emergencyContactsTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && emergencyContactsTable.getSelectedRow() >= 0) {
					new EditEmergencyContactAction().actionPerformed(null);
				}
			}
		});
		emergencyContactsTable.setFillsViewportHeight(true);
		emergencyContactsTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emergencyContactsTable.getTableHeader().setReorderingAllowed(false);
		emergencyContactsTable.setModel(ecModel);
		TableRowSorter<TableModel> ecTableSorter = new TableRowSorter<>(emergencyContactsTable.getModel());
		emergencyContactsTable.setRowSorter(ecTableSorter);
		emergencyContactsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ecPane.setViewportView(emergencyContactsTable);
		
		lblGPA = new JLabel(String.format("%.2f", student.calculateGPA()));
		lblGPA.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblGPA, "cell 2 3,push ,aligny center");
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		contentPane.add(separator_2, "cell 1 3,growy");
		emergencyContactsTable.setDefaultEditor(Object.class, null);
		
		setUpKeyBinds();
	}
	
	private void setUpKeyBinds() {
		JComponent rootPane = this.getRootPane();
		
		// Input Mapping
		rootPane.getInputMap(inFocusedWindow).put(ctrlR, REFRESH);
		rootPane.getInputMap(inFocusedWindow).put(ctrlQ, EXIT);
		registeredCoursesTable.getInputMap(isFocused).put(del, REMOVE);
		registeredCoursesTable.getInputMap(isFocused).put(bksp, REMOVE);
		emergencyContactsTable.getInputMap(isFocused).put(del, REMOVE);
		emergencyContactsTable.getInputMap(isFocused).put(bksp, REMOVE);
		
		// Action Mapping.
		rootPane.getActionMap().put(REFRESH, new RefreshAllAction());
		rootPane.getActionMap().put(EXIT, new ExitWindowAction());
		registeredCoursesTable.getActionMap().put(REMOVE, new RemoveCourseAction());
		emergencyContactsTable.getActionMap().put(REMOVE, new RemoveContactAction());
		
	}
	
	private class RefreshCoursesAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			rcModel.setDataVector(student.getRegisteredCourses(), rcColumns);
			lblGPA.setText(String.format("%.2f", student.calculateGPA()));
			lblAveGrade.setText(String.format("%.2f", student.calculateAverageGrade()));
		}
	}
	
	private class RefreshContactsAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			ecModel.setDataVector(student.getEmergencyContacts(), ecColumns);
		}
	}
	
	private class RefreshInfoAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			lblLNM.setText(student.getLegalName());
			lblPNM.setText(student.getPreferredName());
			lblGND.setText(student.getGender());
			lblPNS.setText(student.getPronouns());
			lblEML.setText(student.getEmail());
		}
	}
	
	private class RefreshAllAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			new RefreshCoursesAction().actionPerformed(null);
			new RefreshContactsAction().actionPerformed(null);
			new RefreshInfoAction().actionPerformed(null);
		}
	}
	
	private class EditStudentAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			EditStudent editStudentWindow = new EditStudent(student, shdl);
			editStudentWindow.setVisible(true);
		}
	}
	
	private class AddContactAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			AddEmergencyContact addEmergencyContactWindow = new AddEmergencyContact(student, shdl);
			addEmergencyContactWindow.setVisible(true);
		}
	}
	
	private class EditEmergencyContactAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int row = emergencyContactsTable.getSelectedRow();
			EditEmergencyContact editECWindow = new EditEmergencyContact(student.getEmergencyContactByID((String) emergencyContactsTable.getValueAt(row, 0)), student, shdl);
			editECWindow.setVisible(true);
		}
	}
	
	private class RemoveContactAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
			
			if (confirm == JOptionPane.NO_OPTION)
				return;
			
			int col = 0;
			int row = emergencyContactsTable.getSelectedRow();
			
			try {
				String contactID = (String) emergencyContactsTable.getValueAt(row, col);
				shdl.deleteContactFromEmergencyContacts(contactID);
				student.removeEmergencyContact(contactID);
				new RefreshContactsAction().actionPerformed(null);
			} catch (Exception e2) {
				System.out.println("An Error Occurred.");
				e2.printStackTrace();
			}
		}
	}
	
	private class AddCourseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			AddRegisteredCourse addRegisteredCourseWindow = new AddRegisteredCourse(student, shdl);
			addRegisteredCourseWindow.setVisible(true);
		}
	}
	
	private class UpdateCourseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = registeredCoursesTable.getSelectedRow();
			
			try {
				String grade = JOptionPane.showInputDialog("What would you like to change the grade to?");
				if (grade == null || grade.isBlank() || !grade.matches("[0-9.]+")) {
					JOptionPane.showMessageDialog(null, "Invalid Grade Input", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Double dblGrade = Double.parseDouble(grade);
				
				if (dblGrade < 0.00) {
					JOptionPane.showMessageDialog(null, "Grade Must Not Subceed 0.00", "", JOptionPane.WARNING_MESSAGE);
					return;
				} else if (dblGrade > 100.00) {
					JOptionPane.showMessageDialog(null, "Grade Must Not Exceed 100.00", "", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				String courseID = (String) registeredCoursesTable.getValueAt(row, col);
				student.updateCourseGrade(courseID, dblGrade);
				shdl.updateRegisteredCourseInfo(student.getID(), student.getRegisteredCourseByID(courseID));
				new RefreshCoursesAction().actionPerformed(null);
			} catch (Exception e2) {
				System.out.println("An Error Occurred.");
				e2.printStackTrace();
				return;
			}
		}
	}
	
	private class RemoveCourseAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
			
			if (confirm == JOptionPane.NO_OPTION)
				return;
			
			int col = 0;
			int row = registeredCoursesTable.getSelectedRow();
			
			try {
				String courseID = (String) registeredCoursesTable.getValueAt(row, col);
				shdl.deleteCourseFromRegisteredCourses(courseID);
				student.removeRegisteredCourse(courseID);
				new RefreshCoursesAction().actionPerformed(null);
			} catch (Exception e2) {
				System.out.println("An Error Occurred.");
				e2.printStackTrace();
			}
		}
	}
	
	private class ExitWindowAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
