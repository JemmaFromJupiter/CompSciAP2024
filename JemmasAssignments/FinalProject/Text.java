import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Text extends JLabel {
  public Text() {
    super();
  }

  public Text(String text) {
    super(text);
  }

  public Text(String text, int alignment) {
    super(text);
    this.setHorizontalAlignment(alignment);
  }
}
