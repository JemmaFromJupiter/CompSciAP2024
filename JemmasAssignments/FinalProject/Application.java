


import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable studentTable;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application frame = new Application();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Application() {
		setTitle("Overview");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024, 640);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JScrollPane studentPane = new JScrollPane();
		studentPane.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		studentPane.setBounds(206, 10, 794, 583);
		studentPane.setPreferredSize(new Dimension(794, 583));
		getContentPane().add(studentPane);
		
		studentTable = new JTable();
		studentTable.setDefaultEditor(Object.class, null);
		studentTable.getTableHeader().setReorderingAllowed(false);
		studentTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student ID", "Legal Name", "Preferred Name", "Gender", "Date of Birth"
			}
		));
		studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentTable.setFillsViewportHeight(true);
		studentTable.setPreferredScrollableViewportSize(studentPane.getPreferredSize());
		studentPane.setViewportView(studentTable);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		buttonPanel.setBounds(10, 10, 175, 583);
		getContentPane().add(buttonPanel);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(194, 10, 2, 583);
		getContentPane().add(separator);
	}
}
