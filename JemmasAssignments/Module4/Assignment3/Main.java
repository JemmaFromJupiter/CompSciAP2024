import java.io.File;
import java.security.InvalidParameterException;

public class Main {

  public static void main(String[] args) {
    File textFile;
    if (args != null) {
      switch (args[0]) {
        case "--compress":
          textFile = new File(args[1]);
          Compressor comp = new Compressor(textFile);
          comp.compress();
        case "--decompress":
          File encodedFile = new File(args[1]);
          Decompressor decmp = new Decompressor(encodedFile);
          decmp.decompress();
      }
    } else {
      throw new InvalidParameterException(String.format("Invalid Argument %s", args[0]));
    }
  }

}
