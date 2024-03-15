import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Window extends JFrame {

  private String windowID;

  public Window(String windowID, int width, int height) {
    super();
    this.windowID = windowID;
    this.setSize(width, height);
    this.setup();
  }

  public Window(String windowID, Dimension d) {
    super();
    this.windowID = windowID;
    this.setSize(d);
    this.setup();
  }

  public Window(String windowID, String title, int width, int height) {
    super(title);
    this.setSize(width, height);
    this.setup();
  }

  public Window(String windowID, String title, Dimension d) {
    super(title);
    this.setSize(d);
    this.setup();
  }

  private void setup() {
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
    this.addWindowListener(new CheckClosed());
  }

  private class CheckClosed implements WindowListener {
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
      ConfirmWindow confWin = new ConfirmWindow();
      confWin.setVisible(true);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

  }

  private class ConfirmWindow extends Window implements ActionListener {

    private Text confText = new Text("Are you sure you want to close the application?", SwingConstants.CENTER);

    private Button confButton = new Button("Confirm");
    private Button cancelButton = new Button("Cancel");

    private Panel buttonPanel = new Panel(new GridLayout(1, 2));

    public ConfirmWindow() {
      super("win.conf", 600, 300);
      this.setResizable(false);
      this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
      this.setLayout(new BorderLayout());

      this.confButton.addActionListener(this);
      this.cancelButton.addActionListener(this);

      this.buttonPanel.addComponentGroup(new JComponent[] { this.confButton, this.cancelButton });

      this.addComponentGroup(new JComponent[] { this.confText, this.buttonPanel },
          new Object[] { BorderLayout.CENTER, BorderLayout.SOUTH });

    }

    public void actionPerformed(ActionEvent e) {
      String actionCommand = e.getActionCommand();

      switch (actionCommand) {
        case "Confirm":
          System.exit(0);
          break;
        case "Cancel":
          this.dispose();
          break;
        default:
          System.out.println("An Error Occurred In The Confirmation Window.");
          break;
      }
    }

  }

  public void addComponentGroup(JComponent[] components) {
    for (JComponent jc : components) {
      this.add(jc);
    }
  }

  public void addComponentGroup(JComponent[] components, Object[] constraints) {
    if (!(components.length == constraints.length))
      return;

    for (int i = 0; i < components.length; i++) {
      this.add(components[i], constraints[i]);
    }
  }

}

class MainWindow extends Window {

  private String[] columnNames = { "Student ID", "Legal Name", "Preferred Name", "Gender", "Pronouns",
      "Date of Birth" };

  private Panel tablePanel = new Panel();

  private JScrollPane tableScroll;
  private JTable studentTable;

  private StudentViewWindow svw = new StudentViewWindow(this.getWidth(), this.getHeight());

  private RuntimeDatabase rdb;

  public MainWindow(int width, int height, RuntimeDatabase rdb) {
    super("win.main", "Overview", width, height);
    this.rdb = rdb;
    this.setUp();
  }

  public MainWindow(Dimension d, RuntimeDatabase rdb) {
    super("win.main", "Overview", d);
    this.rdb = rdb;
    this.setUp();
  }

  private void setUp() {
    this.studentTable = new JTable(this.rdb.asArray(), this.columnNames);
    this.studentTable.setDefaultEditor(Object.class, null);

    this.tableScroll = new JScrollPane(this.studentTable);
    this.tableScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    this.tableScroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

    this.setUpListeners();

    this.setLayout(new BorderLayout());
    this.tablePanel.setLayout(new BorderLayout());

    this.tablePanel.addComponentGroup(new JComponent[] { studentTable.getTableHeader(), this.tableScroll },
        new Object[] { BorderLayout.NORTH, BorderLayout.CENTER });

    this.add(this.tablePanel, BorderLayout.CENTER);
  }

  private void setUpListeners() {
    this.studentTable.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        JTable table = (JTable) e.getSource();
        Point point = e.getPoint();
        int row = table.rowAtPoint(point);
        if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
          //
        }
      }
    });
  }

}

class StudentViewWindow extends Window {

  private Student viewedStudent;

  public StudentViewWindow(int width, int height) {
    super("win.student.view", width, height);
  }

  public StudentViewWindow(Dimension d) {
    super("win.student.view", d);
  }

  public void viewStudent(Student s) {
    this.viewedStudent = s;

  }

}
