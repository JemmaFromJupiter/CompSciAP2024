import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class Decompressor {
  private File compressedFile;
  private String fileContent;
  private HashMap<Character, String> lookupTable;
  private Node root;

  public Decompressor(File compressedFile) {
    this.compressedFile = compressedFile;
    this.root = null;
  }

  public void decompress() {
  }
}
