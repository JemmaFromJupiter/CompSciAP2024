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
	
	// KeyStrokes for key bindings.
	private KeyStroke ctrlA = KeyStroke.getKeyStroke("control A");
	private final String ADD = "student+";
	
	private KeyStroke del = KeyStroke.getKeyStroke("DELETE");
	private KeyStroke bksp = KeyStroke.getKeyStroke("BACK_SPACE");
	private final String REMOVE = "student-";
	
	private KeyStroke ctrlV = KeyStroke.getKeyStroke("control V");
	private final String VIEW = "view";
	
	private KeyStroke ctrlQ = KeyStroke.getKeyStroke("control Q");
	private final String QUIT = "quit";
	
	// Rest of setup
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
		
		JMenu mnNewMenu = new JMenu("Actions");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmAddStudent = new JMenuItem(String.format("Add Student%50s", "Ctrl+A"));
		mntmAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddStudentAction().actionPerformed(null);
			}
		});
		mnNewMenu.add(mntmAddStudent);
		
		JMenuItem mntmRemoveStudent = new JMenuItem(String.format("Remove Student%45s", "Del or Back"));
		mntmRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveStudentAction().actionPerformed(null);
			}
		});
		mnNewMenu.add(mntmRemoveStudent);
		
		JMenuItem mntmViewStudent = new JMenuItem(String.format("View Student%49s", "Ctrl+V"));
		mntmViewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ViewStudentAction().actionPerformed(null);
			}
		});
		
		mnNewMenu.add(mntmViewStudent);
		
		JMenuItem mntmExit = new JMenuItem(String.format("Exit%65s", "Ctrl+Q"));
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new QuitAction().actionPerformed(null);
			}
		});
		mnNewMenu.add(mntmExit);
		
		JMenu mnNewMenu_1 = new JMenu("Help");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnNewMenu_1.add(mntmAbout);
		
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
				if (e.getClickCount() == 2) {
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
		
		studentTable.getInputMap(isFocused).put(ctrlV, VIEW);
		studentTable.getActionMap().put(VIEW, new ViewStudentAction());
		
		this.getRootPane().getInputMap(inFocusedWindow).put(ctrlQ, QUIT);
		this.getRootPane().getActionMap().put(QUIT, new QuitAction());
	}
	
	private class AddStudentAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			System.out.println(e);
			AddStudent addStudentWindow = new AddStudent(shdl, rdb, studentTableModel);
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
				studentTableModel.removeRow(row);
				rdb.pop(row);
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
			ViewStudent viewStudentWindow = new ViewStudent(rdb.getStudentByID((String) studentTable.getValueAt(row, col)), shdl, rdb);
			viewStudentWindow.setVisible(true);
		}
	}
	
	private class QuitAction extends AbstractAction {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}