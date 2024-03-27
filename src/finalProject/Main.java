package finalProject;

import java.awt.EventQueue;
import javax.swing.UIManager;

public class Main {
  public static void main(String[] args) {
	  try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) {
	        e.printStackTrace();
	        return;
	   }
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
}
