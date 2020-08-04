package model.data;

import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DatumQueryServiceTest {
    private static DatumQueryService queryService;

    @BeforeAll
    static void setUp() {
        queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
    }

    @Test
    void getScopedSearch() throws NotFoundException {
        Item q42 = new Item("Q42", queryService);
        assertEquals(q42, queryService.getScopedSearch(q42).getItem());
    }

    @Test
    void getNameByID() throws NotFoundException {
        assertEquals("universe", queryService.getNameByID("Q1"));
    }

    @Test
    void getQualifiersByStatement() throws NotFoundException {
        Item q42 = new Item("Q42", queryService);
        assertEquals(0, queryService.getQualifiersByStatement(
                new Statement(q42, "P31", queryService), q42).size());
    }

    @Test
    void getReferencesByStatement() throws NotFoundException {
        Item q42 = new Item("Q42", queryService);
        assertEquals(0, queryService.getQualifiersByStatement(
                new Statement(q42, "P31", queryService), q42).size());
    }

    @Test
    void getDescriptionByID() throws NotFoundException {
        assertEquals("English writer and humorist", queryService.getDescriptionByID("Q42"));
    }
}