package model.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StringBuilderUtil {
    /*
     * Class Description:
     * This is a utility class which I made because I was doing a lot of padding of string builders and I wanted a
     * nice way to do that functionally and to reduce code redundancy. This class is made of entirely static methods
     * so it can not be instantiated.
     */
    public StringBuilderUtil() {
        throw new Error("StringBuilderUtil contains only static methods");
    }

    /*
     * REQUIRES: list is not null, additionalLength is not negative
     * MODIFIES: list
     * EFFECTS : padds all of the string builders in the list with the given character such that they are all the
     * same length plus an additional length given
     */
    public static void padAll(List<StringBuilder> list, char character, int additionalLength) {
        int length = list.stream()
                .max(Comparator.comparingInt(StringBuilder::length))
                .orElseGet(StringBuilder::new)
                .length() + additionalLength;
        list.forEach((i) -> pad(i, character, length));
    }

    /*
     * REQUIRES: builder is not null, length is not negative
     * MODIFIES: builder
     * EFFECTS : padds a string builder with the given character such that it is the length required
     */
    public static void pad(StringBuilder builder, char character, int length) {
        IntStream.range(0, length - builder.length()).mapToObj((i) -> character).forEach(builder::append);
    }
}
