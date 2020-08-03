package model.data;

import model.data.pages.Item;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.ItemViewController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ScopedSearchTest {
    private ScopedSearch scopedSearch;
    private Item q42;

    @BeforeEach
    void setUp() {
        q42 = new Item("Q42", new DatumQueryService(new WebCollector()));
        scopedSearch = new ScopedSearch(q42, q42.getQuery());
    }

    @Test
    void getItem() {
        assertEquals(q42, scopedSearch.getItem());
        assertNull(new ScopedSearch(new ItemViewController(null), q42.getQuery()).getItem());
    }
}