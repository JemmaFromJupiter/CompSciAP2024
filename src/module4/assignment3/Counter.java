package module4.assignment3;

import java.util.HashMap;

public class Counter extends HashMap<Character, Integer> {
  // Simply counts the number of characters in a String/File and stores the
  // frequency with a Character key.
  public Counter() {

  }

  public Counter(String s) {
    this.count(s);
  }

  public void count(String s) {
    for (char c : s.toCharArray()) {
      if (!this.containsKey(c)) {
        this.put(c, 1);
      } else {
        this.put(c, this.get(c) + 1);
      }
    }
  }

}
