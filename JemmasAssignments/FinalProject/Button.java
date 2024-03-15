import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Button extends JButton {
  public Button() {
    super();
  }

  public Button(int width, int height) {
    super();
    this.setSize(width, height);
  }

  public Button(Dimension d) {
    super();
    this.setSize(d);
  }

  public Button(String text) {
    super(text);
  }

  public Button(String text, int width, int height) {
    super(text);
    this.setSize(width, height);
  }

  public Button(String text, Dimension d) {
    super(text);
    this.setSize(d);
  }

}
