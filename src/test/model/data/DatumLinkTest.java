package model.data;

import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DatumLinkTest {
    private static DatumLink datumLink;

    @BeforeAll
    static void setUp() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        Item q42 = new Item("Q42", queryService);
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
        List<StringBuilder> stringArray = datumLink.getStatements().toStringArray();
        StringBuilder result = new StringBuilder();
        for (StringBuilder builder : stringArray) {
            result.append(builder).append("\n");
        }
        assertEquals("",
                result.toString());
    }

    @Test
    void parse() {
        assertFalse(datumLink.parse(new ArrayList<>()));
    }
}