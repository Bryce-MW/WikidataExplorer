package model.util;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StringBuilderUtil {
    public StringBuilderUtil() {
        throw new Error("StringBuilderUtil contains only static methods");
    }

    public static void padAll(@NotNull List<StringBuilder> list, char character, int additionalLength) {
        int length = list.stream()
                .max(Comparator.comparingInt(StringBuilder::length))
                .orElseGet(StringBuilder::new)
                .length() + additionalLength;
        list.forEach((i) -> pad(i, character, length));
    }

    public static void pad(@NotNull StringBuilder builder, char character, int length) {
        IntStream.range(0, length - builder.length()).mapToObj((i) -> character).forEach(builder::append);
    }
}
