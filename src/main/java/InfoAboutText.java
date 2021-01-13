import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InfoAboutText {
    final static String fullText = getResourceFileAsString("Data");
    final static String specialWords = getResourceFileAsString("Special-words");

    public static void show() {
        System.out.println("the number of all words in the text - "
                + getNumberOfAllWords());

        System.out.println("list without special words and" +
                " without words shorter than 3 characters - "
                + withoutSpecialWords(specialWords));

        System.out.println("an array of special words and their number - " +
                Arrays.toString(withoutNormalWords(specialWords)) + " " +
                Arrays.toString(withoutNormalWords(specialWords)).length());

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
        assert fullText != null;
        return fullText
                .replaceAll("[,()!'.]", "").split(" ");
    }

    public static int getNumberOfAllWords() {
        return getArrayFromString().length;
    }

    public static List<String> withoutSpecialWords(String specialWords) {
        String[] fullText = getArrayFromString();
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
        String[] fullText = getArrayFromString();
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
