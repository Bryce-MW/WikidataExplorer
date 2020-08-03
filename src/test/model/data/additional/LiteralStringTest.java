package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LiteralStringTest {
    private static LiteralString additional;

    @BeforeAll
    static void setUp() {
        additional = new LiteralString("Test", new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("Test", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("Test", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("Test", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}