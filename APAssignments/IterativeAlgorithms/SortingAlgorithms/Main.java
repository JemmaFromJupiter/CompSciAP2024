
public class Main {

  static int[] ls = { 5, 6, 11, 8, 10, 3 };
  static int[] sls = { 2, 5, 7, 9, 10, 13, 17, 18, 27, 36 };

  public static void main(String[] args) {
    System.out.println(linearSearch(ls, 8));
    System.out.println(binarySearch(sls, 7));
  }

  public static int linearSearch(int[] list, int value) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == value)
        return i;
    }
    return -1;
  }

  public static int binarySearch(int[] list, int value) {
    int low = 0;
    int high = list.length;

    while (low <= high) {
      int mid = (int) (high + low) / 2;

      if (value == list[mid])
        return mid;
      else if (value > list[mid])
        low = mid + 1;
      else
        high = mid - 1;
    }
    return -1;
  }

}

