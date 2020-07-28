package model.data;

import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatumQueryServiceTest {
    private static DatumQueryService queryService;

    @BeforeAll
    static void setUp() {
        queryService = new DatumQueryService(new WebCollector());
    }

    @Test
    void getScopedSearch() {
        Item q42 = new Item("Q42", queryService);
        assertEquals(q42, queryService.getScopedSearch(q42).getItem());
    }

    @Test
    void getNameByID() {
        assertEquals("universe", queryService.getNameByID("Q1"));
    }

    @Test
    void getQualifiersByStatement() {
        Item q42 = new Item("Q42", queryService);
        assertEquals(0, queryService.getQualifiersByStatement(
                new Statement(q42, "P31", queryService), q42).size());
    }

    @Test
    void getReferencesByStatement() {
        Item q42 = new Item("Q42", queryService);
        assertEquals(0, queryService.getQualifiersByStatement(
                new Statement(q42, "P31", queryService), q42).size());
    }

    @Test
    void getDescriptionByID() {
        assertEquals("English writer and humorist", queryService.getDescriptionByID("Q42"));
    }
}