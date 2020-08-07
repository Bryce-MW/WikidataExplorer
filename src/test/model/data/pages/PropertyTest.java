package model.data.pages;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.additional.helpers.EntityData;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PropertyTest {
    private static Property entity;
    private static DatumQueryService queryService;

    @BeforeAll
    static void setUp() throws NotFoundException {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        entity = new Property("P31", queryService);
    }

    @Test
    void getTitle() {
        assertEquals("instance of", entity.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("that class of which this subject is a particular example and member",
                entity.getDescription());
    }

    @Test
    void getID() {
        assertEquals("P31", entity.getID());
    }

    @Test
    void getStatements() {
        assertEquals(10, entity.getStatements().toStringArray().size());
    }

    @Test
    void alternateConstruct() {
        Map<String, Object> value = new HashMap<>();
        value.put("entity-type", "item");
        value.put("id", "Q30");
        value.put("numeric-id", 30D);
        EntityData data = new EntityData(value);
        assertThrows(NotFoundException.class, () -> new Property(data, queryService));
    }
}