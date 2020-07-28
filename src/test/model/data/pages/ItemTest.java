package model.data.pages;

import model.data.DatumQueryService;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemTest {
    private static Item entity;

    @BeforeAll
    static void setUp() {
        entity = new Item("Q42", new DatumQueryService(new WebCollector()));
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
        assertEquals(0, entity.getStatements().toStringArray().size());
    }
}