package model.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringBuilderUtilTest {

    @Test
    void stringBuilderUtil() {
        assertThrows(Error.class, StringBuilderUtil::new);
    }

    @Test
    void padAll() {
        ArrayList<StringBuilder> test = new ArrayList<>(3);
        test.add(new StringBuilder("12345"));
        test.add(new StringBuilder("1234567"));
        test.add(new StringBuilder("123456"));

        StringBuilderUtil.padAll(test, ' ', 3);

        for (StringBuilder builder : test) {
            assertEquals(10, builder.length());
        }
    }

    @Test
    void pad() {
        StringBuilder test = new StringBuilder("1234567");
        StringBuilderUtil.pad(test, '-', 10);
        assertEquals(test.toString(), "1234567---");
    }
}