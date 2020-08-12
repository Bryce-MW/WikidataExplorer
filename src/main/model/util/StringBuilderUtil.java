package model.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StringBuilderUtil {
    /*
     * Class Description:
     *
     */
    public StringBuilderUtil() {
        throw new Error("StringBuilderUtil contains only static methods");
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public static void padAll(List<StringBuilder> list, char character, int additionalLength) {
        int length = list.stream()
                .max(Comparator.comparingInt(StringBuilder::length))
                .orElseGet(StringBuilder::new)
                .length() + additionalLength;
        list.forEach((i) -> pad(i, character, length));
    }

    /*
     * REQUIRES:
     * MODIFIES:
     * EFFECTS :
     */
    public static void pad(StringBuilder builder, char character, int length) {
        IntStream.range(0, length - builder.length()).mapToObj((i) -> character).forEach(builder::append);
    }
}
