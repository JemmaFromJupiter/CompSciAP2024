import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

public class Decompressor {
  // Class that allows for the decompression of the compressed files made by the
  // Compression class.

  private File compressedFile;
  private String path;
  private byte[] encodedData;
  private String encodedBinary;
  private Map<String, Character> decodingMap;

  public Decompressor(File compressedFile) {
    this.compressedFile = compressedFile;
    this.path = this.compressedFile.getAbsolutePath();
    this.encodedData = new byte[(int) this.compressedFile.length()];
    this.readLookup();
    this.readEncodedData();
  }

  private void readLookup() {
    // Reads the lookup table and appends each lookup value to a hashmap that is
    // read by the decompressor.
    //
    this.decodingMap = new HashMap<>();
    // a buffered reader is used and it looks for the ".lookup" file.
    try (BufferedReader lookupReader = new BufferedReader(
        new FileReader(this.path.substring(0, this.path.lastIndexOf(".")) + ".lookup"))) {
      String line;
      while ((line = lookupReader.readLine()) != null) {
        // Splits the lines by the delimiter and appends each side appropriately.
        String[] parts = line.split("->");
        decodingMap.put(parts[1], parts[0].equals("\\n") ? '\n'
            : parts[0].equals("\\r") ? '\r' : parts[0].equals("\\") ? '\\' : parts[0].charAt(0));
      }
      lookupReader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void readEncodedData() {
    // is handled with a file input stream and sets the file contents
    // "encodedBinary" to the binary value of each byte.
    StringBuilder encodedBinary = new StringBuilder();
    try (FileInputStream inputStream = new FileInputStream(this.compressedFile)) {
      inputStream.read(this.encodedData);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (byte b : this.encodedData) {
      encodedBinary.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
    }
    this.encodedBinary = encodedBinary.toString();

  }

  private String decodeData() {
    StringBuilder decodedData = new StringBuilder();
    StringBuilder currentCode = new StringBuilder();

    // This for loop checks through each character of the encoded binary data and
    // checks to see if the decoding map has the code that is generated. If it does,
    // add the corresponding character and delete the codes value to iterate again.
    for (int i = 0; i < this.encodedBinary.length(); i++) {
      currentCode.append(this.encodedBinary.charAt(i));
      if (this.decodingMap.containsKey(currentCode.toString())) {
        decodedData.append(this.decodingMap.get(currentCode.toString()));
        currentCode.setLength(0);
      }
    }
    return decodedData.toString();
  }

  private void writeToFile() {
    // Writes the decompressed data to a new file and deletes the .bin and .lookup.

    File lookup = new File(this.path.substring(0, this.path.lastIndexOf(".")) + ".lookup");
    File newFile = new File(this.path.substring(0, this.path.lastIndexOf(".")) + ".txt");
    try {
      newFile.createNewFile();
      FileWriter fileWriter = new FileWriter(newFile);
      fileWriter.write(this.encodedBinary);
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (this.compressedFile.delete()) {
      lookup.delete();
    }

  }

  public void decompress() {
    this.encodedBinary = decodeData();
    this.writeToFile();
  }
}
