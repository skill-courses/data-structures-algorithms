package algorithms.kmp;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KMP {

    public static int kmpSearch(String originalStr, String targetStr) {
        final var table = getPartialMatchTable(targetStr);

        for (int index = 0, targetIndex = 0; index < originalStr.length(); index++) {

            while (targetIndex > 0 && originalStr.charAt(index) != targetStr.charAt(targetIndex)) {
                targetIndex = table[targetIndex - 1];
            }

            if (originalStr.charAt(index) == targetStr.charAt(targetIndex)) {
                targetIndex++;
            }

            if (targetIndex == targetStr.length()) {
                return index - targetIndex + 1;
            }
        }

        return -1;
    }

    public static int[] getPartialMatchTableByLoop(String pattern) {
        int[] table = new int[pattern.length()];
        table[0] = 0;
        for (int i = 1, j = 0; i < pattern.length(); i++) {

            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
            }
            table[i] = j;
        }

        return table;
    }

    public static int[] getPartialMatchTable(String pattern) {
        return getPrefixes(pattern).stream().mapToInt(KMP::getMaxCommonSubStrLength).toArray();
    }

    public static int getMaxCommonSubStrLength(String pattern) {
        final var prefixes = getPrefixes(pattern);
        prefixes.remove(pattern);
        final var postfixes = getPostfixes(pattern);
        return prefixes.stream().filter(postfixes::contains)
                .max(Comparator.comparingInt(String::length))
                .map(String::length).orElse(0);
    }

    public static List<String> getPrefixes(String str) {
        return IntStream.rangeClosed(1, str.length())
                .mapToObj(i -> str.substring(0, i))
                .collect(Collectors.toList());
    }

    public static Set<String> getPostfixes(String str) {
        return IntStream.range(1, str.length())
                .mapToObj(str::substring)
                .collect(Collectors.toSet());
    }
}
