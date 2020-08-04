package model.data.pages;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyTest {
    private static Property entity;

    @BeforeAll
    static void setUp() throws NotFoundException {
        entity = new Property("P31", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
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
}