package model.data.additional;

import model.data.DatumQueryService;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimeTest {
    private static Time additional;

    @BeforeAll
    static void setUp() {
        additional = new Time("+2001-12-31T00:00:00Z", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("+2001-12-31T00:00:00Z", additional.getTitle());
    }

    @Test
    void getDescription() {
        assertNull(additional.getDescription());
    }

    @Test
    void getID() {
        assertEquals("+2001-12-31T00:00:00Z", additional.getID());
    }

    @Test
    void getStatements() {
        assertNull(additional.getStatements());
    }
}