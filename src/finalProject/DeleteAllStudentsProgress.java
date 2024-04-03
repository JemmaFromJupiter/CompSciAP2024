/**
 * This file does not matter in the scope of the final goal of the project.
 * just for testing and nice display for testing.
 */

package finalProject;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

public class DeleteAllStudentsProgress extends JFrame {
	
	private static StorageHandler shdl;
	private static RuntimeDatabase rdb;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JProgressBar progressBar;
	private static ProgressWorker progWorker;
	private static JLabel lblNewLabel;
	private static JLabel status;

	/**
	 * Create the frame.
	 */
	public DeleteAllStudentsProgress(StorageHandler shdl_, RuntimeDatabase rdb_) {
		shdl = shdl_;
		rdb = rdb_;
		
		run();
	}
	
	public void run() {
		setTitle("Populating Database...");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(512, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[][][][][]"));
		
		lblNewLabel = new JLabel("Deleting Students...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel, "cell 0 1,push ,grow");
		
		status = new JLabel("New label");
		contentPane.add(status, "cell 0 3");
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(progressBar, "cell 0 4,push ,growx");
		
		progWorker = new ProgressWorker(progressBar);
	}
	
	public static void showDialog(StorageHandler shdl, RuntimeDatabase rdb) {
		DeleteAllStudentsProgress ptp = new DeleteAllStudentsProgress(shdl, rdb);
		ptp.setVisible(true);
		progWorker.execute();
	}
	
	private static class ProgressWorker extends SwingWorker<Void, Integer> {
		private static JProgressBar progress;
		private static int i = rdb.size();
		
		public ProgressWorker(JProgressBar progress_) {
			progress = progress_;
		}
		
		@Override
		protected Void doInBackground() throws Exception {
			progressBar.setMaximum(i);
			
			while (!rdb.isEmpty()) {
				try {
					Student s = rdb.pop();
					shdl.deleteFromDatabase(s.getID());
					status.setText(String.format("Successfully Deleted Student %s...", s.getLegalName()));
					
					final int prog = (int) ((100L * (i - rdb.size())) / 100);
					publish(prog);
				} catch (Exception tE) {
					System.out.println("Something went wrong...");
					tE.printStackTrace();
					break;
				}
			}
			return null;
		}
		
		@Override
		protected void process(List<Integer> chunks) {
			progress.setValue(chunks.get(chunks.size() - 1));
			super.process(chunks);
		}
		
		@Override
		protected void done() {
			lblNewLabel.setText("Finished!");
			status.setText(String.format("Successfully Deleted %d Students From The Database.", i));
			progress.setValue(i);
		}
	}

}