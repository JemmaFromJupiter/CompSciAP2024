import java.io.File;
import java.security.InvalidParameterException;

public class Main {

  public static void main(String[] args) {
    File textFile;
    if (args != null) {
      textFile = new File(args[0]);
      Compressor comp = new Compressor(textFile);
      comp.compress();
    } else {
      throw new InvalidParameterException(String.format("Invalid Argument %s", args[0]));
    }
  }

}
