import java.io.File;

public class Main {

  public static void main(String[] args) {
    File beeScript = new File("./beeScript.txt");
    Compressor comp = new Compressor(beeScript);

    comp.compress();
    comp.printData();
  }

}
