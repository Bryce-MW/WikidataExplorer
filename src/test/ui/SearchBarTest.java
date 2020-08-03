package ui;

import model.data.DatumQueryService;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalRepository;
import model.data.source.WebCollector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.SearchBar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SearchBarTest {
    private SearchBar searchBar;

    @BeforeEach
    void setUp() {
        DatumQueryService queryService = new DatumQueryService(new WebCollector(new LocalRepository("wikidata.json")));
        searchBar = new SearchBar(new ScopedSearch(new Item("Q42", queryService), queryService));
    }

    @Test
    void getResults() {
        assertEquals(0, searchBar.getResults().size());
        // Currently always empty, searching not supported yet!
    }

    @Test
    void search() {
        assertNull(searchBar.search("Anything"));
        // Currently always null, searching not supported yet!
    }

    @Test
    void testToString() {
        assertEquals("S: Search", searchBar.toString());
    }
}