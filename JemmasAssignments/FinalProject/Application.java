import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Application {

  private StorageHandler shdl = new StorageHandler();
  private RuntimeDatabase rdb = new RuntimeDatabase();

  private Window MainWindow;
  private final int WIDTH = 300;
  private final int HEIGHT = 200;
  private final int POPUPWIDTH = 200;
  private final int POPUPHEIGHT = 300;

  public void setUpApp() {

  }

}

class Window extends JFrame {

  private class CheckOnExit implements WindowListener {
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
      ConfirmWindow checkers = new ConfirmWindow();
      checkers.setVisible(true);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
  }

  private class ConfirmWindow extends Window implements ActionListener {

    public ConfirmWindow() {
      this.setSize(200, 100);
      this.getContentPane().setBackground(Color.YELLOW);
      this.setLayout(new BorderLayout());

      JLabel confirmLabel = new JLabel("Are you sure you want to exit?");
      this.add(confirmLabel, BorderLayout.CENTER);

      Panel buttonPanel = new Panel();
      buttonPanel.setBackground(Color.ORANGE);
      buttonPanel.setLayout(new FlowLayout());

      Button exitButton = new Button("Yes");
      exitButton.addActionListener(this);

      Button cancelButton = new Button("No");
      cancelButton.addActionListener(this);

      buttonPanel.addComponents(new JComponent[] { exitButton, cancelButton });

      this.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
      String actionCommand = e.getActionCommand();

      switch (actionCommand) {
        case "Yes":
          System.exit(0);
        case "No":
          this.dispose();
        default:
          System.out.println("An Error Occurred In The ConfirmWindow.");
      }
    }
  }

  public void addComponents(JComponent[] Components) {
    for (JComponent jc : Components) {
      this.add(jc);
      jc.setVisible(true);
    }
  }

}

class Panel extends JPanel {

  public void addComponents(JComponent[] Components) {
    for (JComponent jc : Components) {
      this.add(jc);
      jc.setVisible(true);
    }
  }

}

class Button extends JButton {

  public Button() {
    super();
  }

  public Button(String text) {
    super(text);
  }

  public Button(Action a) {
    super(a);
  }

  public Button(String text, int x, int y, int width, int height) {
    super(text);
    this.setBounds(x, y, width, height);
  }

  public Button(int x, int y, int width, int height) {
    super();
    this.setBounds(x, y, width, height);
  }

}
