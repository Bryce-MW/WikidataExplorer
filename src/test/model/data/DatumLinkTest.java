package model.data;

import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DatumLinkTest {
    private static DatumLink datumLink;
    private static DatumQueryService queryService;
    private static Item q42;

    @BeforeAll
    static void setUp() {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        q42 = new Item("Q42", queryService);
        datumLink = new DatumLink(queryService, new Statement(q42, "P31", queryService), new Item("Q1",
                queryService));
    }

    @Test
    void getTitle() {
        assertEquals("universe", datumLink.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("totality consisting of space, time, matter and energy", datumLink.getDescription());
    }

    @Test
    void getID() {
        assertEquals("Q1", datumLink.getID());
    }

    @Test
    void getStatements() {
        assertNull(datumLink.getStatements());
    }
}