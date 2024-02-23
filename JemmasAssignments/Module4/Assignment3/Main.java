import java.io.File;
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
    textFile = new File(argument);
    comp = new Compressor(textFile);
    comp.compress();
  }

  public static void decompress(String argument) {
    compressedFile = new File(argument);
    decmp = new Decompressor(compressedFile);
    decmp.decompress();

  }

}
