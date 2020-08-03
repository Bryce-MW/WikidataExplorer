package model.data.pages;

import model.data.DatumQueryService;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyTest {
    private static Property entity;

    @BeforeAll
    static void setUp() {
        entity = new Property("P31", new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json"))));
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
        assertEquals(0, entity.getStatements().toStringArray().size());
    }
}