import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InfoAboutText {

    public static void show() {
        System.out.println("the number of all words in the text - "
                + getNumberOfAllWords("Data"));

        System.out.println("list without special words and" +
                " without words shorter than 3 characters - "
                + withoutSpecialWords("Special-words"));

        System.out.println("an array of special words and their number - " +
                Arrays.toString(withoutNormalWords("Special-words")) + " " +
                Arrays.toString(withoutNormalWords("Special-words")).length());

        System.out.println("popular words - " + valueComparator(4));


    }

    public static String getResourceFileAsString(String fileName) {
        InputStream is = InfoAboutText.class.getClassLoader().getResourceAsStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(" ")).toLowerCase();
        }
        return null;
    }

    public static String[] getArrayFromString() {
        return Objects.requireNonNull(getResourceFileAsString("Data"))
                .replaceAll("[,()!'.]", "").split(" ");
    }

    public static int getNumberOfAllWords(final String text) {
        return getArrayFromString().length;
    }

    public static List<String> withoutSpecialWords(String specialWords) {
        List<String> fullText = Arrays.asList(getArrayFromString());
        List<String> onlyNormalWords = new LinkedList<>();
        for (String word : fullText) {
            if (specialWords.contains(word) || word.length() < 3) {
                continue;
            } else {
                onlyNormalWords.add(word);
            }
        }
        return onlyNormalWords;
    }

    public static String[] withoutNormalWords(String specialWords) {
        List<String> fullText = Arrays.asList(getArrayFromString());
        List<String> listSpecialWords = new LinkedList<>();
        for (String word : fullText) {
            if (specialWords.contains(word) || word.length() < 3) {
                listSpecialWords.add(word);
            }
        }
        String[] arraySpecialWords = new String[listSpecialWords.size()];
        for (int i = 0; i < listSpecialWords.size(); i++) {
            arraySpecialWords[i] = listSpecialWords.get(i);
        }
        return arraySpecialWords;
    }

    public static List<String> valueComparator(int n) {
        List<String> list = Arrays.asList(getArrayFromString());
        List<String> result = new ArrayList<>();
        Map<String, Integer> dd = new HashMap<>();
        for (String word : list) {
            dd.put(word, Collections.frequency(list, word));
        }
        List<Map.Entry<String, Integer>> f = dd.entrySet()
                .stream()
                .sorted((x, y) -> y.getValue().compareTo(x.getValue()))
                .collect(Collectors.toList());

        List<String> input = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input.add(f.get(i).getKey() + " "
                    + Collections.frequency(list, f.get(i).getKey()) + " reps");
        }
        return input;
    }
}
