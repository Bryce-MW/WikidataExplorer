package model.data.pages;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ItemTest {
    private static Item entity;

    @BeforeAll
    static void setUp() throws NotFoundException {
        entity = new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
    }

    @Test
    void getTitle() {
        assertEquals("Douglas Adams", entity.getTitle());
    }

    @Test
    void getDescription() {
        assertEquals("English writer and humorist", entity.getDescription());
    }

    @Test
    void getID() {
        assertEquals("Q42", entity.getID());
    }

    @Test
    void getStatements() {
        assertEquals(10, entity.getStatements().toStringArray().size());
    }

    @Test
    void testParse() {
        assertFalse(entity.parse(new ArrayList<>())); //False for any input
    }

    @Test
    void testHashCode() {
        assertEquals(56414707, entity.hashCode());
    }
}