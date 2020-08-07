package ui;

import model.data.DatumQueryService;
import model.data.NotFoundException;
import model.data.ScopedSearch;
import model.data.pages.Item;
import model.data.source.LocalCollector;
import model.data.source.LocalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.cli.SearchBar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SearchBarTest {
    private SearchBar searchBar;

    @BeforeEach
    void setUp() throws NotFoundException {
        DatumQueryService queryService = new DatumQueryService(new LocalCollector(new LocalRepository("wikidata.json")));
        searchBar = new SearchBar(new ScopedSearch(new Item("Q42", queryService), queryService));
    }

    @Test
    void getResults() {
        assertEquals(0, searchBar.getResults().size());
        // Currently always empty, searching not supported yet!
    }

    @Test
    void search() {
        assertEquals(0, searchBar.search("Anything").size());
        assertEquals("Q42", searchBar.search("Q42").get(0).getID());
    }

    @Test
    void testToString() {
        assertEquals("S: Search", searchBar.toString());
    }

    @Test
    void parse() {
        assertFalse(searchBar.parse(new ArrayList<>()));
    }
}