package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ExternalIdentifierTest {
    private static ExternalIdentifier additional;

    @BeforeAll
    static void setUp() {
        additional =
                new ExternalIdentifier("test", new DatumQueryService(
                        new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("test", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("test", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("test", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}