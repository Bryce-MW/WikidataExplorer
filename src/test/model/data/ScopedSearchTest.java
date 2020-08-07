package model.data;

import model.data.pages.Item;
import model.data.pages.Property;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemView;
import ui.cli.ItemViewController;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ScopedSearchTest {
    private ScopedSearch scopedSearch;
    private Item q42;

    @BeforeEach
    void setUp() throws NotFoundException {
        q42 = new Item("Q42", new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json"))));
        scopedSearch = new ScopedSearch(q42, q42.getQuery());
    }

    @Test
    void getItem() {
        assertEquals(q42, scopedSearch.getItem());
        assertNull(new ScopedSearch(new ItemViewController(new ItemView(q42)), q42.getQuery()).getItem());
    }

    @Test
    void add() {
        ScopedSearch search = new ScopedSearch(new ItemViewController(new ItemView(q42)), q42.queryService);
        ArrayList<Value> values = new ArrayList<>();
        values.add(q42);
        assertDoesNotThrow(() -> search.add(values));
    }

    @Test
    void findElement() throws NotFoundException {
        ScopedSearch search = new ScopedSearch(new ItemViewController(new ItemView(q42)), q42.queryService);
        assertEquals(new Property("P800", q42.queryService), search.findElement("P800").get(0));
        assertEquals(0, search.findElement("P1").size());
    }
}