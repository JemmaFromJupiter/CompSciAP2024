import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Dict<T> extends HashMap<String, T> {
  /**
   * A simple implementation of a Dict class that stores a hashmap that can be
   * used to store items with keys.
   * Dicts must be declared with strict typing. Ex: Dict<String> can only have
   * String Type values.
   * Dictionaries have to be declared as such: `Dict<String> myDict = new
   * Dict<>(new Object[][]{{key, value}})`
   * 
   * @param dictVals An Array representation of the dictionary Objects. {{key1,
   *                 value1}, {key2, value2}}
   */

  Dict(Object[][] dictVals) {
    super(validate_and_initialise(dictVals));
  }

  @SuppressWarnings("unchecked")
  private static <T> Map<String, T> validate_and_initialise(Object[][] dictVals) {
    if (dictVals == null) {
      throw new IllegalArgumentException("Dictionary Values Must Not Be NULL");
    }

    Map<String, T> map = new HashMap<>();
    for (Object[] item : dictVals) {
      if (item == null || item.length != 2 || !(item[0] instanceof String)) {
        throw new IllegalArgumentException("Invalid Dictionary Item At: " + item.toString());
      }

      try {
        map.put((String) item[0], (T) item[1]);
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Invalid Dictionary Item Type: " + Arrays.toString(item), e);
      }
    }
    return map;
  }

  Dict(Map<String, T> initialMap) {
    super(validate_and_initialise(initialMap));
  }

  private static <T> Map<String, T> validate_and_initialise(Map<String, T> initialMap) {
    if (initialMap == null) {
      throw new IllegalArgumentException("Initial Map Must Not Be Empty");
    }

    return new HashMap<>(initialMap);
  }

  T get(String key) {
    return super.get(key);
  }

  T get(String key, T default_value) {
    T value = super.get(key);
    return value != null ? value : default_value;
  }

  @Override
  public T put(String key, T value) {
    return super.put(key, value);
  }

  @Override
  public String toString() {
    StringBuilder dictString = new StringBuilder("{");

    boolean first = true;
    for (Map.Entry<String, T> entry : entrySet()) {
      if (!first) {
        dictString.append(", ");
      }
      // System.out.println(entry.getValue().getClass());
      dictString.append("\"").append(entry.getKey()).append("\": ");
      if (entry.getValue() instanceof String) {
        dictString.append("\"").append(entry.getValue()).append("\"");
      } else {
        dictString.append(entry.getValue());
      }
      first = false;
    }

    dictString.append("}");
    return dictString.toString();
  }

}

public class Main {
  public static void main(String[] args) {
    Dict<Float> itemPrices = new Dict<>(new Object[][] {
        { "Bag of Apples", 10.97f },
        { "Bag of Oranges", 9.99f },
        { "Bunch of Bananas", 9.76f }
    });

    System.out.println(itemPrices);
    itemPrices.put("Frozen Pizza", 19.99f);
    System.out.println(itemPrices);
  }
}
