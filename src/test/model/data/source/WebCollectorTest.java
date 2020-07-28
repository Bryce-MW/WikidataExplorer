package model.data.source;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WebCollectorTest {
    private static WebCollector webCollector;

    @BeforeAll
    static void setUp() {
        webCollector = new WebCollector();
    }

    @Test
    void getEntityName() {
        assertEquals("Douglas Adams", webCollector.getEntityName("Q42"));
        assertThrows(NullPointerException.class, () -> webCollector.getEntityName("qwerty"));
    }

    @Test
    void getQualifiers() {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add("Q42");
        tree.add("P119");
        tree.add("Q533697");
        assertEquals(0, webCollector.getQualifiers(tree).size()); // 0 because qualifiers not implemented

        tree = new ArrayList<>(3);
        tree.add("Q2");
        tree.add("P361");
        tree.add("Q18589965");
        assertEquals(0, webCollector.getQualifiers(tree).size()); // 0 because qualifiers not implemented

        tree = new ArrayList<>(3);
        tree.add("Q2");
        tree.add("P361");
        tree.add("Q18589966");
        assertEquals(0, webCollector.getQualifiers(tree).size()); // 0 because qualifiers not implemented

    }

    @Test
    void getReferences() {
        ArrayList<String> tree = new ArrayList<>(3);
        tree.add("Q42");
        tree.add("P119");
        tree.add("Q533697");
        assertEquals(0, webCollector.getReferences(tree).size()); // 0 because qualifiers not implemented
    }

    @Test
    void getEntityDescription() {
        assertEquals("third planet from the Sun in the Solar System",
                webCollector.getEntityDescription("Q2"));
    }
}