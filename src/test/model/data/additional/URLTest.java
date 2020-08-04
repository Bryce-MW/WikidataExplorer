package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class URLTest {
    private static URL additional;

    @BeforeAll
    static void setUp() {
        additional = new URL("example.com",
                new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("example.com", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("example.com", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("example.com", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}