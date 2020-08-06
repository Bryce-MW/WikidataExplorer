package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CommonsMediaTest {
    private static CommonsMedia additional;

    @BeforeAll
    static void setUp() {
        additional = new CommonsMedia("",
                new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("", additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }

}