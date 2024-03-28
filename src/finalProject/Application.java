package finalProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.util.*;

public class Application extends JFrame {
	
	private String[] columnNames = new String[] {"Student ID", "Legal Name", "Preferred Name", "Gender", "Date of Birth"};
	
	private final int isFocused = JComponent.WHEN_FOCUSED;
	private final int inFocusedWindow = JComponent.WHEN_IN_FOCUSED_WINDOW;
	
	// Keyboard actions using KeyStrokes. Used to create key bindings for specific application functions.
	private KeyStroke ctrlA = KeyStroke.getKeyStroke("control A");
	private final String ADD = "student+";
	
	private KeyStroke del = KeyStroke.getKeyStroke("DELETE");
	private KeyStroke bksp = KeyStroke.getKeyStroke("BACK_SPACE");
	private final String REMOVE = "student-";
	
	private KeyStroke ctrlR = KeyStroke.getKeyStroke("control R");
	private final String REFRESH = "rfsh";
	
	private KeyStroke ctrlQ = KeyStroke.getKeyStroke("control Q");
	private final String QUIT = "quit";
	
	// Defines the used storage handler and other variables that are assigned a value later in the code.
	private StorageHandler shdl = new StorageHandler();
	private RuntimeDatabase rdb;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable studentTable;
	private DefaultTableModel studentTableModel;
	
	private void setup() {
		try {
			shdl.setUpConnection();
			shdl.queryDatabase("SELECT s.*, rc.*, ec.* FROM students s LEFT JOIN registered_courses rc ON s.STUDENT_ID = rc.STUDENT_ID LEFT JOIN emergency_contacts ec ON s.STUDENT_ID = ec.STUDENT_ID;");
			rdb = shdl.createRuntimeDatabase();
		} catch (Exception e) {
			System.out.println("An Error Occurred.");
			e.printStackTrace();
			return;
		}
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		this.setup();
		studentTableModel = new DefaultTableModel(this.rdb.asArray(), columnNames);
		
		setTitle("Student Overview");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 640);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setMargin(new Insets(0, 0, 0, 50));
		setJMenuBar(menuBar);
		
		JMenu mnActions = new JMenu("Actions");
		menuBar.add(mnActions);
		
		JMenuItem mntmAddStudent = new JMenuItem(String.format("Add Student%50s", "CTRL+A"));
		mntmAddStudent.addActionListener(new AddStudentAction());
		mnActions.add(mntmAddStudent);
		
		JMenuItem mntmRemoveStudent = new JMenuItem(String.format("Remove Student%40s", "DEL"));
		mntmRemoveStudent.addActionListener(new RemoveStudentAction());
		mnActions.add(mntmRemoveStudent);
		
		JMenuItem mntmRefresh = new JMenuItem(String.format("Refresh%58s", "CTRL+R"));
		mntmRefresh.addActionListener(new RefreshTableAction());
		mnActions.add(mntmRefresh);
		
		JMenuItem mntmExit = new JMenuItem(String.format("Exit%65s", "CTRL+Q"));
		mntmExit.addActionListener(new QuitAction());
		mnActions.add(mntmExit);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane studentPane = new JScrollPane();
		studentPane.setBounds(10, 10, 995, 555);
		studentPane.setViewportBorder(null);
		studentPane.setPreferredSize(new Dimension(794, 583));
		getContentPane().add(studentPane);
		
		studentTable = new JTable();
		studentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && studentTable.getSelectedRow() >= 0) {
					new ViewStudentAction().actionPerformed(null);
				}
			}
		});
		studentTable.setFillsViewportHeight(true);
		studentTable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentTable.setDefaultEditor(Object.class, null);
		studentTable.getTableHeader().setReorderingAllowed(false);
		studentTable.setModel(studentTableModel);
		studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		TableRowSorter<TableModel> tableSorter = new TableRowSorter<>(studentTable.getModel());
		
		tableSorter.setComparator(0, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Integer num1 = Integer.parseInt(o1.toString());
                Integer num2 = Integer.parseInt(o2.toString());
                return num1.compareTo(num2);
            }
        });
		
		studentTable.setRowSorter(tableSorter);
		studentTable.setPreferredScrollableViewportSize(studentPane.getPreferredSize());
		studentPane.setViewportView(studentTable);
		
		setUpKeyBindings();
	}
	
	private void setUpKeyBindings() {
		studentTable.getInputMap(isFocused).put(ctrlA, ADD);
		studentTable.getActionMap().put(ADD, new AddStudentAction());
		
		studentTable.getInputMap(isFocused).put(del, REMOVE); // If delete key is present on users keyboard.
		studentTable.getInputMap(isFocused).put(bksp, REMOVE); // If delete key is NOT present on users keyboard.
		studentTable.getActionMap().put(REMOVE, new RemoveStudentAction());
		
		studentTable.getInputMap(isFocused).put(ctrlR, REFRESH);
		studentTable.getActionMap().put(REFRESH, new RefreshTableAction());;
		
		this.getRootPane().getInputMap(inFocusedWindow).put(ctrlQ, QUIT);
		this.getRootPane().getActionMap().put(QUIT, new QuitAction());
	}
	
	private class RefreshTableAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			studentTableModel.setDataVector(rdb.asArray(), columnNames);
		}
	}
	
	private class AddStudentAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			AddStudent addStudentWindow = new AddStudent(shdl, rdb);
			addStudentWindow.setVisible(true);
		}
	}
	
	private class RemoveStudentAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			
			int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you would like to delete this student?", "Confirm Remove Student", JOptionPane.YES_NO_OPTION);
			
			if (confirm == JOptionPane.NO_OPTION)
				return;
			
			int col = 0;
			int row = studentTable.getSelectedRow();
			
			if (row < 0) {
				System.out.println("There is no student selected.");
				return;
			}
			
			try {
				String studentID = (String) studentTable.getValueAt(row, col);
				shdl.deleteFromDatabase(studentID);
				rdb.pop(row);
				new RefreshTableAction().actionPerformed(null);
			} catch (Exception e2) {
				System.out.println("An Error Occurred.");
				e2.printStackTrace();
			}
		}
	}
	
	private class ViewStudentAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			int col = 0;
			int row = studentTable.getSelectedRow();
			if (row < 0) {
				System.out.println("There is no student selected.");
				return;
			}
			ViewStudent viewStudentWindow = new ViewStudent(rdb.getStudentByID((String) studentTable.getValueAt(row, col)), shdl);
			viewStudentWindow.setVisible(true);
		}
	}
	
	private class QuitAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}