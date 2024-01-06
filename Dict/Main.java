import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class Dict<T> extends HashMap<String, T> {

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
        Dict<String> fst = new Dict<>(new Object[][]{{"1", "First"}, {"2", "Second"}, {"3", "Third"}});
        Dict<String> itemPrices = new Dict<>(new Object[][]{
            {"Bag of Apples", 10.97f},
            {"Bag of Oranges", 9.99f},
            {"Bunch of Bananas", 9.76f}
        });

        System.out.println(fst);
    }
}