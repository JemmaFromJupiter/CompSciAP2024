import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel {
  public Panel() {
    super();
  }

  public Panel(LayoutManager layout) {
    super(layout);
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
