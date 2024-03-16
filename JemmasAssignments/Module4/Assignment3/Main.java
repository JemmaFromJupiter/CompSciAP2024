
import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;

public class Main {
  public static File textFile;
  public static File compressedFile;
  public static Compressor comp;
  public static Decompressor decmp;

  public static void main(String[] args) {
    switch (args[0]) {
      case "--compress":
        compress(args[1]);
        break;
      case "-c":
        compress(args[1]);
        break;
      case "--decompress":
        decompress(args[1]);
        break;
      case "-d":
        decompress(args[1]);
        break;
      default:
        throw new InvalidParameterException(String.format("Invalid Argument %s", args[0]));
    }
  }

  public static void compress(String argument) {
    try {
      textFile = new File(argument);
      comp = new Compressor(textFile);
      comp.compress();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void decompress(String argument) {
    try {
      compressedFile = new File(argument);
      decmp = new Decompressor(compressedFile);
      decmp.decompress();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
