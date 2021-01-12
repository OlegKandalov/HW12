import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class InfoAboutText {
    public static void main(String[] args) {
        String allText = getResourceFileAsString("Data");
        String specialWords = getResourceFileAsString("Special-words");

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
        for (String word : list) {
            result.add("Слово " + word + " повторяется " + Collections.frequency(list, word));
        }
        List<String> f = result
                .stream()
                .distinct()
                .limit(n)
                .collect(Collectors.toList());
        Collections.sort(f);
        return f;
    }
}
