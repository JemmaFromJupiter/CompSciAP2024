package finalProject;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

import java.util.Random;

public class AddEmergencyContact extends JFrame {
	
	private static Random random = new Random();

	private Student student;
	private StorageHandler shdl;
	private RuntimeDatabase rdb;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField contactName;
	private JLabel status;
	private JTextField contactHome;
	private JTextField contactCell;
	private JTextField contactEmail;

	/**
	 * Create the frame.
	 */
	public AddEmergencyContact(Student s, StorageHandler shdl) {
		this.student = s;
		this.shdl = shdl;
		
		setTitle("Add Registered Course");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 394);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][]", "[][][grow][grow]"));
		
		JLabel lblRequired = new JLabel("* - Required Field");
		lblRequired.setForeground(Color.RED);
		lblRequired.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblRequired, "flowx,cell 0 0 2 1,growx");
		
		JLabel lblOpt_1_2 = new JLabel("* - Only One Required");
		lblOpt_1_2.setForeground(Color.ORANGE);
		lblOpt_1_2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lblOpt_1_2, "cell 0 0,growx");
		
		status = new JLabel(" ");
		status.setForeground(Color.RED);
		contentPane.add(status, "cell 0 2 2 1,push ,growx,aligny center");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 1 2 1,push ,growx,aligny center");
		panel.setLayout(new MigLayout("", "[grow][grow]", "[][][][][][][][]"));
		
		JLabel lblContactName = new JLabel("Contact Name");
		lblContactName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblContactName, "flowx,cell 0 0");
		
		contactName = new JTextField();
		contactName.setColumns(10);
		panel.add(contactName, "cell 0 1 2 1,growx");
		
		JLabel lblContactHome = new JLabel("Home");
		lblContactHome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblContactHome, "flowx,cell 0 3");
		
		JLabel lblContactCell = new JLabel("Cell");
		lblContactCell.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblContactCell, "flowx,cell 1 3");
		
		contactHome = new JTextField();
		panel.add(contactHome, "cell 0 4,growx");
		contactHome.setColumns(10);
		
		contactCell = new JTextField();
		contactCell.setColumns(10);
		panel.add(contactCell, "cell 1 4,growx");
		
		JLabel lblContactEmail = new JLabel("Email");
		lblContactEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblContactEmail, "flowx,cell 0 6");
		
		JLabel lblReq_1 = new JLabel("*");
		lblReq_1.setForeground(Color.RED);
		lblReq_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblReq_1, "cell 0 0");
		
		JLabel lblOpt = new JLabel("*");
		lblOpt.setForeground(Color.ORANGE);
		lblOpt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblOpt, "cell 0 3");
		
		JLabel lblOpt_1 = new JLabel("*");
		lblOpt_1.setForeground(Color.ORANGE);
		lblOpt_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblOpt_1, "cell 1 3");
		
		JLabel lblOpt_1_1 = new JLabel("*");
		lblOpt_1_1.setForeground(Color.ORANGE);
		lblOpt_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel.add(lblOpt_1_1, "cell 0 6");
		
		contactEmail = new JTextField();
		contactEmail.setColumns(10);
		panel.add(contactEmail, "cell 0 7 2 1,growx");
		
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, "cell 0 3 2 1,grow");
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
		String idString = Integer.toHexString(random.nextInt());
		String nameString = contactName.getText().strip();
		String cellString = contactCell.getText().strip();
		String homeString = contactHome.getText().strip();
		String emailString = contactEmail.getText().strip();
		
		if (idString.isBlank() || nameString.isBlank()) {
			status.setText("All of the required fields must be filled out.");
			return;
		}
		
		if (cellString.isBlank() && homeString.isBlank() && emailString.isBlank()) {
			status.setText("At least one of the contact methods must be filled out.");
			return;
		}
		
		try {
			student.addEmergencyContact(idString, nameString, homeString, cellString, emailString);
			shdl.addEmergencyContactToDatabase(student.getID(), student.getEmergencyContactByID(idString));
		} catch (Exception e) {
			e.printStackTrace();
		}
		dispose();
	}

}
