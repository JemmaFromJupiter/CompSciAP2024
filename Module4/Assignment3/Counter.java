import java.util.HashMap;

public class Counter extends HashMap<Character, Integer> {

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
